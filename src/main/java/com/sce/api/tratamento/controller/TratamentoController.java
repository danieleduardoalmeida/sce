package com.sce.api.tratamento.controller;

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

import com.sce.api.tratamento.exception.TratamentoApiException;
import com.sce.api.tratamento.model.TratamentoResponse;
import com.sce.api.tratamento.model.TratamentoVo;
import com.sce.api.tratamento.service.TratamentoService;

@RestController
@RequestMapping("/v1/sce")
public class TratamentoController {

	@Autowired
	private TratamentoService tratamentoService;
	
	@PostMapping("/tratamento")
	public ResponseEntity<TratamentoResponse> postTratamento(@RequestBody TratamentoVo vo) throws TratamentoApiException {
		TratamentoResponse reponse = tratamentoService.salvar(vo);
		return ResponseEntity.ok(reponse);
	}
	
	@PutMapping("/tratamento/{id}")
    public ResponseEntity<TratamentoResponse> putTratamento(@RequestBody TratamentoVo vo,
                                                      @PathVariable(PATH_PARAM_ID) Long id) throws TratamentoApiException  {
        TratamentoResponse response = tratamentoService.atualizar(vo, id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/tratamento/{id}")
    public ResponseEntity<Object> deleteTratamento(@PathVariable(PATH_PARAM_ID) Long id) throws TratamentoApiException {
        tratamentoService.remover(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/tratamento/vistoria/{id}")
    public ResponseEntity<Object> deleteTratamentoByVistoriaId(@PathVariable(PATH_PARAM_ID) Long id) throws TratamentoApiException {
        tratamentoService.removerByVistoriaId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tratamento/{id}")
    public ResponseEntity<TratamentoResponse> getTratamento(@PathVariable(PATH_PARAM_ID) Long id) throws TratamentoApiException {
        TratamentoResponse response = tratamentoService.pesquisarTratamentoById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tratamento")
    public ResponseEntity<List<TratamentoResponse>> getTratamentos() {
        List<TratamentoResponse> response = tratamentoService.listarTodos();
        return ResponseEntity.ok(response);
    }
}
