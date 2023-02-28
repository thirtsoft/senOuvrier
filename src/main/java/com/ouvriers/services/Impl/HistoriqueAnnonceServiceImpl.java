package com.ouvriers.services.Impl;

import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.HistoriqueAnnonce;
import com.ouvriers.repository.HistoriqueAnnonceRepository;
import com.ouvriers.services.HistoriqueAnnonceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class HistoriqueAnnonceServiceImpl implements HistoriqueAnnonceService {

    private final HistoriqueAnnonceRepository historiqueAnnonceRepository;

    public HistoriqueAnnonceServiceImpl(HistoriqueAnnonceRepository historiqueAnnonceRepository) {
        this.historiqueAnnonceRepository = historiqueAnnonceRepository;
    }

    @Override
    public HistoriqueAnnonce save(HistoriqueAnnonce historiqueAnnonceDto) {
        return historiqueAnnonceRepository.save(historiqueAnnonceDto);
    }

    @Override
    public HistoriqueAnnonce update(Long id, HistoriqueAnnonce historiqueAnnonceDto) {
        if (!historiqueAnnonceRepository.existsById(id)) {
            throw new ResourceNotFoundException("HistoriqueAnnonce not found");
        }

        Optional<HistoriqueAnnonce> optionalHistoriqueAnnonce = historiqueAnnonceRepository.findById(id);

        if (!optionalHistoriqueAnnonce.isPresent()) {
            throw new ResourceNotFoundException("HistoriqueAnnonceDto not found");
        }
        HistoriqueAnnonce historiqueAnnonceDtoResult = optionalHistoriqueAnnonce.get();
        historiqueAnnonceDtoResult.setAction(historiqueAnnonceDto.getAction());
        historiqueAnnonceDtoResult.setCreatedDate(historiqueAnnonceDto.getCreatedDate());
        historiqueAnnonceDtoResult.setAnnonce(historiqueAnnonceDto.getAnnonce());

        return historiqueAnnonceRepository.save(historiqueAnnonceDtoResult);
    }

    @Override
    public HistoriqueAnnonce findById(Long id) {
        if (id == null) {
            log.error("HistoriqueAnnonce Id is null");
            return null;
        }

        Optional<HistoriqueAnnonce> optionalHistoriqueAnnonce = historiqueAnnonceRepository.findById(id);

        return Optional.of(optionalHistoriqueAnnonce.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun HistoriqueAnnonce avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<HistoriqueAnnonce> findAll() {
        return historiqueAnnonceRepository.findAll();
    }

    @Override
    public List<HistoriqueAnnonce> findHistoriqueAnnonceByOrderByIdDesc() {
        return historiqueAnnonceRepository.findHistoriqueAnnonceByOrderByIdDesc();
    }

    @Override
    public long countNumbersOfHistoriqueAnnonces() {
        return historiqueAnnonceRepository.count();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("HistoriqueAnnonce not found");
            return;
        }
        historiqueAnnonceRepository.deleteById(id);

    }
}
