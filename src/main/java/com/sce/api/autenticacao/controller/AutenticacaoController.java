package com.sce.api.autenticacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.autenticacao.exception.AutenticacaoApiException;
import com.sce.api.autenticacao.model.AutenticacaoResponse;
import com.sce.api.autenticacao.model.AutenticacaoVo;
import com.sce.api.autenticacao.service.AutenticacaoService;

@RestController
@RequestMapping("/v1/sce")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/autenticacao")
    public ResponseEntity<AutenticacaoResponse> postAmostra(@RequestBody AutenticacaoVo vo) throws AutenticacaoApiException  {
        AutenticacaoResponse response = autenticacaoService.autenticarUsuario(vo);
        return ResponseEntity.ok(response);
    }

}
