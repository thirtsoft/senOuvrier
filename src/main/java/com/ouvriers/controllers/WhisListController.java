package com.ouvriers.controllers;

import com.ouvriers.controllers.api.WhistListApi;
import com.ouvriers.models.WhistList;
import com.ouvriers.services.WhistListService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
public class WhisListController implements WhistListApi {

    private final WhistListService whistListService;


    @Override
    public ResponseEntity<WhistList> save(WhistList whistList) {
        return null;
    }

    @Override
    public ResponseEntity<WhistList> update(Long id, WhistList whistList) {
        return null;
    }

    @Override
    public ResponseEntity<WhistList> getWhistListById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<WhistList> getWhistListByRerefence(String reference) {
        return null;
    }

    @Override
    public ResponseEntity<List<WhistList>> getAllWhistLists() {
        return null;
    }

    @Override
    public ResponseEntity<List<WhistList>> getListOfWhistListsByKeyword(String keyword) {
        return null;
    }

    @Override
    public Page<WhistList> getListWhistListByPageable(int page, int size) {
        return null;
    }

    @Override
    public Page<WhistList> getWhistListByLocalityPageables(Long addId, int page, int size) {
        return null;
    }
}
