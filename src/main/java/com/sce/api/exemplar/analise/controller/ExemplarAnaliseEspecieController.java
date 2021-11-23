package com.sce.api.exemplar.analise.controller;

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

import com.sce.api.exemplar.analise.exception.ExemplarAnaliseApiException;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieResponse;
import com.sce.api.exemplar.analise.model.ExemplarAnaliseEspecieVo;
import com.sce.api.exemplar.analise.service.ExemplarAnaliseEspecieService;
import com.sce.api.imovel.exception.ImovelApiException;

@RestController
@RequestMapping("/v1/sce")
public class ExemplarAnaliseEspecieController {

	@Autowired
	private ExemplarAnaliseEspecieService exemplarAnaliseService;
	
	@PostMapping("/exemplarAnalise")
	public ResponseEntity<ExemplarAnaliseEspecieResponse> postExemplarAnalise(@RequestBody ExemplarAnaliseEspecieVo vo) throws ExemplarAnaliseApiException {
	    ExemplarAnaliseEspecieResponse response = exemplarAnaliseService.salvar(vo);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/exemplarAnalise")
    public ResponseEntity<List<ExemplarAnaliseEspecieResponse>> getExemplarAnalises() {
        List<ExemplarAnaliseEspecieResponse> response = exemplarAnaliseService.listarTodos();
        return ResponseEntity.ok(response);
    }
	
    @GetMapping("/exemplarAnalise/analise/{id}")
    public ResponseEntity<List<ExemplarAnaliseEspecieResponse>> getExemplarAnaliseEspecieByAnaliseId(@PathVariable(PATH_PARAM_ID) Long id) {
        List<ExemplarAnaliseEspecieResponse> response = exemplarAnaliseService.pesquisarExemplarAnaliseEspecieByIdAnalise(id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/exemplarAnalise/analise/{id}")
    public ResponseEntity<Object> deleteAnaliseEspecieByAnaliseId(@PathVariable(PATH_PARAM_ID) Long id) throws ImovelApiException {
        exemplarAnaliseService.remover(id);
        return ResponseEntity.noContent().build();
    }
}