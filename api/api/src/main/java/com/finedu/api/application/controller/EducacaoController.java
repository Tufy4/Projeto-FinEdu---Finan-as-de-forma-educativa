package com.finedu.api.application.controller;

import com.finedu.api.domain.service.EducacaoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/educacao")
public class EducacaoController {

    private final EducacaoService educacaoService;

    public EducacaoController(EducacaoService educacaoService) {
        this.educacaoService = educacaoService;
    }

    @GetMapping
    public String centralConhecimento(Model model) {
        model.addAttribute("artigos", educacaoService.listarArtigosPrincipais());
        return "educacao/central";
    }
}