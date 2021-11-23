package com.sce.api.exemplar.controller;

import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.exemplar.exception.ExemplarApiException;
import com.sce.api.exemplar.model.ExemplarResponse;
import com.sce.api.exemplar.service.ExemplarService;


@RestController
@RequestMapping("/v1/sce")
public class ExemplarController {

	@Autowired
	private ExemplarService exemplarService;
	
	@GetMapping("/exemplar")
	public ResponseEntity<List<ExemplarResponse>> getExemplar() {
		List<ExemplarResponse> response = exemplarService.listarTodos();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/exemplar/{id}")
    public ResponseEntity<ExemplarResponse> getExemplar(@PathVariable(PATH_PARAM_ID) Long id) throws ExemplarApiException {
        ExemplarResponse response = exemplarService.pesquisarExemplarById(id);
        return ResponseEntity.ok(response);
    }
}
