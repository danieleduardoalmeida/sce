package com.sce.api.imovel.controller;

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

import com.sce.api.imovel.exception.ImovelApiException;
import com.sce.api.imovel.model.ImovelResponse;
import com.sce.api.imovel.model.ImovelVo;
import com.sce.api.imovel.service.ImovelRequestValidator;
import com.sce.api.imovel.service.ImovelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/sce")
@Slf4j
public class ImovelController {

    @Autowired
    private ImovelService imovelService;
    @Autowired
    private ImovelRequestValidator validator;

    @PostMapping("/imovel")
    public ResponseEntity<ImovelResponse> postImovel(@RequestBody ImovelVo vo) throws ImovelApiException {
        checkRequest(vo);
        ImovelResponse response = imovelService.salvar(vo);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/imovel/{imovelId}")
    public ResponseEntity<ImovelResponse> putImovel(@PathVariable("imovelId") Long imovelId,
                                                 @RequestBody ImovelVo vo) throws ImovelApiException {
        checkRequest(vo);
        ImovelResponse response = imovelService.atualizar(vo, imovelId);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/imovel")
    public ResponseEntity<List<ImovelResponse>> getImoveis() {
        List<ImovelResponse> response = imovelService.listarTodos();
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/imovel/{imovelId}")
    public ResponseEntity<ImovelResponse> getImovelById(@PathVariable("imovelId") Long imovelId) throws ImovelApiException {
        ImovelResponse response = imovelService.buscarById(imovelId);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/imovel/{id}")
    public ResponseEntity<Object> deleteImovel(@PathVariable(PATH_PARAM_ID) Long id) throws ImovelApiException {
        imovelService.remover(id);
        return ResponseEntity.noContent().build();
    }
    
    private void checkRequest(ImovelVo vo) throws ImovelApiException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(vo, ImovelVo.class.getName());
        ValidationUtils.invokeValidator(validator, vo, bindingResult);
        if (bindingResult.hasErrors()) {
            log.warn(LOG_REQUEST_INVALIDA);
            throw new ImovelApiException(LOG_REQUEST_INVALIDA, bindingResult);
        }
    }
}
