package com.ouvriers.services.Impl;

import com.ouvriers.dtos.WhistListDto;
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
    public WhistListDto save(WhistListDto whistListDto) {
        return WhistListDto.fromEntityToDto(
                whistListRepository.save(
                        WhistListDto.fromDtoToEntity(whistListDto)
                )
        );
    }

    @Override
    public WhistListDto saveNoteToOuvrier(Long idOuv, WhistListDto whistListDto) {
        return null;
    }

    @Override
    public WhistListDto update(Long idWhistList, WhistListDto whistListDto) {
        if (!whistListRepository.existsById(idWhistList)) {
            throw new ResourceNotFoundException("WhistList not found");
        }

        Optional<WhistList> optionalWhistList = whistListRepository.findById(idWhistList);

        if (!optionalWhistList.isPresent()) {
            throw new ResourceNotFoundException("WhistList not found");
        }

        WhistListDto whistListDtoResult = WhistListDto.fromEntityToDto(optionalWhistList.get());
        whistListDtoResult.setNbreEtoile(whistListDto.getNbreEtoile());
        whistListDtoResult.setObservation(whistListDto.getObservation());
        whistListDtoResult.setOuvrierDto(whistListDto.getOuvrierDto());

        return WhistListDto.fromEntityToDto(
                whistListRepository.save(
                        WhistListDto.fromDtoToEntity(whistListDtoResult)
                )
        );
    }

    @Override
    public WhistListDto findById(Long id) {
        if (id == null) {
            log.error("Metier Id is null");
            return null;
        }

        Optional<WhistList> optionalWhistList = whistListRepository.findById(id);

        return Optional.of(WhistListDto.fromEntityToDto(optionalWhistList.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun WhistList avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<WhistListDto> findAll() {
        return whistListRepository.findAll()
                .stream()
                .map(WhistListDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WhistListDto> findTop3RatingOrderByCreatedDateDesc() {
        return whistListRepository.findTop3ByOrderByCreatedDateDesc()
                .stream()
                .map(WhistListDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WhistListDto> findByOrderByIdDesc() {
        return whistListRepository.findByOrderByIdDesc()
                .stream()
                .map(WhistListDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WhistListDto> findTop4ByOrderByCreatedDateDescByOuvrierId(String ouvRef) {
        return whistListRepository.findTop4WhistListOrderByCreatedDateDesc(ouvRef)
                .stream()
                .map(WhistListDto::fromEntityToDto)
                .collect(Collectors.toList());
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
