package com.sce.api.especie.controller;

import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.api.especie.exception.EspecieApiException;
import com.sce.api.especie.model.EspecieResponse;
import com.sce.api.especie.service.EspecieService;

@RestController
@RequestMapping("/v1/sce")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping("/especie")
    public ResponseEntity<List<EspecieResponse>> getEspecies() {
        List<EspecieResponse> response = especieService.listarTodos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/especie/{id}")
    public ResponseEntity<EspecieResponse> getEspecie(@PathVariable(PATH_PARAM_ID) Long id) throws AmostraApiException, EspecieApiException {
        EspecieResponse response = especieService.pesquisarEspecieById(id);
        return ResponseEntity.ok(response);
    }

}
