package com.ouvriers.controllers;

import com.ouvriers.controllers.api.WhistListApi;
import com.ouvriers.dtos.WhistListDto;
import com.ouvriers.services.WhistListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class WhisListController implements WhistListApi {

    private final WhistListService whistListService;

    @Override
    public ResponseEntity<WhistListDto> save(WhistListDto whistListDto) {
        return ResponseEntity.ok(whistListService.save(whistListDto));
    }

    @Override
    public ResponseEntity<WhistListDto> update(Long id, WhistListDto whistListDto) {
        whistListDto.setId(id);
        return ResponseEntity.ok(whistListService.save(whistListDto));
    }

    @Override
    public ResponseEntity<WhistListDto> getWhistListById(Long id) {
        return ResponseEntity.ok(whistListService.findById(id));
    }

    @Override
    public ResponseEntity<WhistListDto> getWhistListByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<WhistListDto>> getAllWhistLists() {
        return ResponseEntity.ok(whistListService.findAll());
    }

    @Override
    public ResponseEntity<List<WhistListDto>> getListOfWhistListsByKeyword(String keyword) {
        return null;
    }

    @Override
    public Page<WhistListDto> getListWhistListByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<WhistListDto> getWhistListByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
