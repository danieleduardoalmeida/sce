package com.sce.api.deposito.controller;

import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.deposito.exception.DepositoApiException;
import com.sce.api.deposito.model.DepositoResponse;
import com.sce.api.deposito.service.DepositoService;

@RestController
@RequestMapping("/v1/sce")
public class DepositoController {
	
	@Autowired
	private DepositoService depositoService;
	
	@GetMapping("/deposito")
	public ResponseEntity<List<DepositoResponse>> getDepositos() {
		List<DepositoResponse> response = depositoService.listarTodos();
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/deposito/{id}")
    public ResponseEntity<DepositoResponse> getDeposito(@PathVariable(PATH_PARAM_ID) Long id) throws DepositoApiException {
	    DepositoResponse response = depositoService.pesquisarDepositoById(id);
        return ResponseEntity.ok(response);
    }

}
