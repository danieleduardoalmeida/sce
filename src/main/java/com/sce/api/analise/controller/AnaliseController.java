package com.sce.api.analise.controller;

import static com.sce.api.ApiConstants.LOG_REQUEST_INVALIDA;
import static com.sce.api.ApiConstants.PATH_PARAM_ID;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.analise.exception.AnaliseApiException;
import com.sce.api.analise.model.AnaliseResponse;
import com.sce.api.analise.model.AnaliseVo;
import com.sce.api.analise.service.AnaliseRequestValidator;
import com.sce.api.analise.service.AnaliseService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class AnaliseController {

	@Autowired
	private AnaliseService analiseService;
	@Autowired
	private AnaliseRequestValidator validator;
	
	@PostMapping("/analise")
	public ResponseEntity<AnaliseResponse> postAnalise(@RequestBody AnaliseVo vo) throws AnaliseApiException {
	    checkRequest(vo);
		AnaliseResponse response = analiseService.salvar(vo);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/analise/{id}")
    public ResponseEntity<AnaliseResponse> putAnalise(@RequestBody AnaliseVo vo,
                                                      @PathVariable(PATH_PARAM_ID) Long id) throws AnaliseApiException  {
	    checkRequest(vo);
        AnaliseResponse response = analiseService.atualizar(vo, id);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/analise/{id}")
    public ResponseEntity<Object> deleteAnalise(@PathVariable(PATH_PARAM_ID) Long id) throws AnaliseApiException {
        analiseService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/analise/{id}")
    public ResponseEntity<AnaliseResponse> getAnalise(@PathVariable(PATH_PARAM_ID) Long id) throws AnaliseApiException {
        AnaliseResponse response = analiseService.pesquisarAnaliseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/analise")
    public ResponseEntity<List<AnaliseResponse>> getAnalises() {
        List<AnaliseResponse> response = analiseService.listarTodos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/analise/aberta")
    public ResponseEntity<List<AnaliseResponse>> getAnalisesAbertas() {
        List<AnaliseResponse> response = analiseService.listarAnalisesAbertas();
        return ResponseEntity.ok(response);
    }
	
	private void checkRequest(AnaliseVo vo) throws AnaliseApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, AnaliseVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new AnaliseApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }
}
