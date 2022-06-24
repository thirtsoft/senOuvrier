package com.ouvriers.services.Impl;


import com.ouvriers.exceptions.ResourceNotFoundException;
import com.ouvriers.models.WhistList;
import com.ouvriers.repository.WhistListRepository;
import com.ouvriers.services.WhistListService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class WhistListServiceImpl implements WhistListService {

    private final WhistListRepository whistListRepository;

    @Override
    public WhistList save(WhistList whistList) {
        return whistListRepository.save(whistList);
    }

    @Override
    public WhistList saveNoteToOuvrier(Long idOuv, WhistList whistList) {
        return null;
    }

    @Override
    public WhistList update(Long idWhistList, WhistList whistList) {
        if (!whistListRepository.existsById(idWhistList)) {
            throw new ResourceNotFoundException("WhistList not found");
        }

        Optional<WhistList> optionalWhistList = whistListRepository.findById(idWhistList);

        if (!optionalWhistList.isPresent()) {
            throw new ResourceNotFoundException("WhistList not found");
        }

        WhistList whistListResult = optionalWhistList.get();
        whistListResult.setNbreEtoile(whistList.getNbreEtoile());
        whistListResult.setObservation(whistList.getObservation());

        return whistListRepository.save(whistListResult);
    }

    @Override
    public WhistList findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<WhistList> optionalWhistList = whistListRepository.findById(id);

        return Optional.of(optionalWhistList.get()).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun WhistList avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<WhistList> findAll() {
        return whistListRepository.findAll();
    }

    @Override
    public List<WhistList> findTop3RatingOrderByCreatedDateDesc() {
        return whistListRepository.findTop3ByOrderByCreatedDateDesc();
    }

    @Override
    public List<WhistList> findByOrderByIdDesc() {
        return whistListRepository.findByOrderByIdDesc();
    }

    @Override
    public List<WhistList> findTop4ByOrderByCreatedDateDescByOuvrierId(String ouvRef) {
        return whistListRepository.findTop4WhistListOrderByCreatedDateDesc(ouvRef);
    }

    @Override
    public BigDecimal countNumberOfNotificationByOuvrierId(String ouvRef) {
        return whistListRepository.countNumberOfWhistListByOuvrierId(ouvRef);
    }

    @Override
    public BigDecimal countNumberOfNotification() {
        return whistListRepository.countNumberOfWhistList();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("WhistList Id is null");
            return;
        }
        whistListRepository.deleteById(id);
    }
}
