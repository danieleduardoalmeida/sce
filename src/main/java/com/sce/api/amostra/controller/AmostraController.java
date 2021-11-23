package com.sce.api.amostra.controller;

import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.amostra.exception.AmostraApiException;
import com.sce.api.amostra.model.AmostraResponse;
import com.sce.api.amostra.model.AmostraVo;
import com.sce.api.amostra.service.AmostraService;

@RestController
@RequestMapping("/v1/sce")
public class AmostraController {

    @Autowired
    private AmostraService amostraService;

    @PostMapping("/amostra")
    public ResponseEntity<AmostraResponse> postAmostra(@RequestBody AmostraVo vo) throws AmostraApiException {
        AmostraResponse response = amostraService.salvar(vo);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/amostra/{id}")
    public ResponseEntity<AmostraResponse> putAmostra(@RequestBody AmostraVo vo,
                                                      @PathVariable(PATH_PARAM_ID) Long id) throws AmostraApiException  {
        AmostraResponse response = amostraService.atualizar(vo, id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/amostra/{id}")
    public ResponseEntity<Object> deleteAmostra(@PathVariable(PATH_PARAM_ID) Long id) throws AmostraApiException {
        amostraService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/amostra/{id}")
    public ResponseEntity<AmostraResponse> getAmostra(@PathVariable(PATH_PARAM_ID) Long id) throws AmostraApiException {
        AmostraResponse response = amostraService.pesquisarAmostraById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/amostra")
    public ResponseEntity<List<AmostraResponse>> getAmostras() {
        List<AmostraResponse> response = amostraService.listarTodos();
        return ResponseEntity.ok(response);
    }

}
