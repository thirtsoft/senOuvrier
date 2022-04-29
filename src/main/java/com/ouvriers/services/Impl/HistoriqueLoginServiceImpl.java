package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.HistoriqueLogin;
import com.ouvriers.repository.HistoriqueLoginRepository;
import com.ouvriers.services.HistoriqueLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class HistoriqueLoginServiceImpl implements HistoriqueLoginService {

    private final HistoriqueLoginRepository historiqueLoginRepository;

    public HistoriqueLoginServiceImpl(HistoriqueLoginRepository historiqueLoginRepository) {
        this.historiqueLoginRepository = historiqueLoginRepository;
    }

    @Override
    public HistoriqueLogin save(HistoriqueLogin historiqueLogin) {
        return historiqueLoginRepository.save(historiqueLogin);
    }

    @Override
    public HistoriqueLogin update(Long idLogin, HistoriqueLogin historiqueLogin) {
        if (!historiqueLoginRepository.existsById(idLogin)) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }

        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(idLogin);

        if (optionalHistoriqueLogin.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueLogin not found");
        }

        HistoriqueLogin historiqueLoginResult = optionalHistoriqueLogin.get();
        historiqueLoginResult.setAction(historiqueLogin.getAction());
        historiqueLoginResult.setCreatedDate(historiqueLogin.getCreatedDate());
        historiqueLoginResult.setUtilisateur(historiqueLogin.getUtilisateur());

        return historiqueLoginRepository.save(historiqueLoginResult);
    }

    @Override
    public HistoriqueLogin findById(Long id) {
        if (id == null) {
            log.error("HistoriqueLogin not found");
            return null;
        }

        Optional<HistoriqueLogin> optionalHistoriqueLogin = historiqueLoginRepository.findById(id);

        return Optional.of(optionalHistoriqueLogin.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueLogin avec l'Id = " + id + "n'a été trouvé")
        );

    }

    @Override
    public List<HistoriqueLogin> findAll() {
        return historiqueLoginRepository.findAll();
    }

    @Override
    public List<HistoriqueLogin> findHistoriqueLoginByOrderByIdDesc() {
        return historiqueLoginRepository.findHistoriqueLoginByOrderByIdDesc();
    }

    @Override
    public long countNumbersOfHistoriqueLogins() {
        return historiqueLoginRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("HistoriqueLogin not found");
            return;
        }
        historiqueLoginRepository.deleteById(id);
    }
}
