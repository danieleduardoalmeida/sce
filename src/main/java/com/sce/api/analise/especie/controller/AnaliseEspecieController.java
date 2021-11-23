package com.sce.api.analise.especie.controller;

import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.analise.especie.exception.AnaliseEspecieApiException;
import com.sce.api.analise.especie.model.AnaliseEspecieResponse;
import com.sce.api.analise.especie.model.AnaliseEspecieVo;
import com.sce.api.analise.especie.service.AnaliseEspecieService;
import com.sce.api.imovel.exception.ImovelApiException;

@RestController
@RequestMapping("/v1/sce")
public class AnaliseEspecieController {

    @Autowired
    private AnaliseEspecieService analiseEspecieService;

    @PostMapping("/analiseEspecie")
    public ResponseEntity<AnaliseEspecieResponse> postAnaliseEspecie(@RequestBody AnaliseEspecieVo vo) throws AnaliseEspecieApiException {
        AnaliseEspecieResponse response = analiseEspecieService.salvar(vo);
        return ResponseEntity.ok(response);
    }
   

    @GetMapping("/analiseEspecie")
    public ResponseEntity<List<AnaliseEspecieResponse>> getAnaliseEspecies() {
        List<AnaliseEspecieResponse> response = analiseEspecieService.listarTodos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/analiseEspecie/analise/{id}")
    public ResponseEntity<List<AnaliseEspecieResponse>> getAnaliseEspecieByAnaliseId(@PathVariable(PATH_PARAM_ID) Long id) {
        List<AnaliseEspecieResponse> response = analiseEspecieService.pesquisarAnaliseEspecieByIdAnalise(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/analiseEspecie/analise/{id}")
    public ResponseEntity<Object> deleteAnaliseEspecieByAnaliseId(@PathVariable(PATH_PARAM_ID) Long id) throws ImovelApiException {
        analiseEspecieService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
