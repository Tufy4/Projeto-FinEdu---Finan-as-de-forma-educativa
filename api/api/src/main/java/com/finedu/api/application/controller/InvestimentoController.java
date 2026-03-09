package com.finedu.api.application.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finedu.api.domain.model.SimulacaoResultado;
import com.finedu.api.domain.service.InvestimentoService;

@Controller
@RequestMapping("/investimentos")
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @GetMapping
    public String exibirSimulador() {
        return "investimentos/simulador";
    }

    @PostMapping("/simular")
    public String processarSimulacao(@RequestParam BigDecimal aporte, 
                                     @RequestParam int meses, 
                                     @RequestParam String ativo, 
                                     Model model) {
    	
        SimulacaoResultado resultado = investimentoService.simular(aporte, meses, ativo);
        
        SimulacaoResultado poupanca = investimentoService.simular(aporte, meses, "POUPANCA");
        
        model.addAttribute("resultado", resultado);
        model.addAttribute("comparativo", poupanca);
        model.addAttribute("aporte", aporte);
        model.addAttribute("meses", meses);
        model.addAttribute("ativoSelecionado", ativo);
        
        return "investimentos/simulador";
    }
}