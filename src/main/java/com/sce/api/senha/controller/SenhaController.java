package com.sce.api.senha.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sce.api.senha.exception.SenhaApiException;
import com.sce.api.senha.model.SenhaVo;
import com.sce.api.senha.service.SenhaService;

@RestController
@RequestMapping("/v1/sce")
public class SenhaController {

    @Autowired
    private SenhaService senhaService;

    @PostMapping("/senha")
    public ResponseEntity<?> postAmostra(@RequestBody SenhaVo vo) throws SenhaApiException   {
        senhaService.alterarSenha(vo);
        return new ResponseEntity<>(ACCEPTED);
    }

}
