package com.sce.api.deposito.vistoria.controller;

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

import com.sce.api.deposito.vistoria.exception.DepositoVistoriaApiException;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaResponse;
import com.sce.api.deposito.vistoria.model.DepositoVistoriaVo;
import com.sce.api.deposito.vistoria.service.DepositoVistoriaService;
import com.sce.api.imovel.exception.ImovelApiException;

@RestController
@RequestMapping("/v1/sce")
public class DepositoVistoriaController {

	@Autowired
	private DepositoVistoriaService depositoVistoriaService;
	
	@PostMapping("/depositoVistoria")
	public ResponseEntity<DepositoVistoriaResponse> postDepositoVistoria(@RequestBody DepositoVistoriaVo vo) throws DepositoVistoriaApiException {
	    DepositoVistoriaResponse reponse = depositoVistoriaService.salvar(vo);
		return ResponseEntity.ok(reponse);
	}
	
	@GetMapping("/depositoVistoria")
    public ResponseEntity<List<DepositoVistoriaResponse>> getDepositoVistorias() {
        List<DepositoVistoriaResponse> response = depositoVistoriaService.listarTodos();
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/depositoVistoria/vistoria/{id}")
    public ResponseEntity<List<DepositoVistoriaResponse>> getDepositoVistoriaByVistoriaId(@PathVariable(PATH_PARAM_ID) Long id) {
        List<DepositoVistoriaResponse> response = depositoVistoriaService.pesquisarDepositoVistoriaByIdVistoria(id);
        return ResponseEntity.ok(response);
    }
	
	@DeleteMapping("/depositoVistoria/vistoria/{id}")
    public ResponseEntity<Object> deleteDepositoVistoriaByVistoriaId(@PathVariable(PATH_PARAM_ID) Long id) throws ImovelApiException {
	    depositoVistoriaService.remover(id);
        return ResponseEntity.noContent().build();
    }

}
