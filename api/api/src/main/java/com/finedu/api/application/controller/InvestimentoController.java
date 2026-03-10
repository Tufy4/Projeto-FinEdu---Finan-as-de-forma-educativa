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
import com.finedu.api.infrastructure.client.MoedaClient;

@Controller
@RequestMapping("/investimentos")
public class InvestimentoController {

    private final InvestimentoService investimentoService;
    private final MoedaClient moedaClient;


    public InvestimentoController(InvestimentoService investimentoService, MoedaClient moedaClient) {
        this.investimentoService = investimentoService;
        this.moedaClient = moedaClient;
    }

    @GetMapping
    public String exibirSimulador(Model model) {
       
        BigDecimal selicAtual = investimentoService.getTaxaSelicAtual();
        model.addAttribute("selicReal", selicAtual.multiply(new BigDecimal("100"))); 
        
        try {
            var cotacoes = moedaClient.buscarCotacoesAtuais(); // Isso vai gerar o LOG no console
            model.addAttribute("taxaUSD", cotacoes.get("USD"));
            model.addAttribute("taxaEUR", cotacoes.get("EUR"));
        } catch (Exception e) {
            model.addAttribute("taxaUSD", 5.05); // Fallback
            model.addAttribute("taxaEUR", 5.42);
        }
        return "investimentos/simulador";
    }

    @PostMapping("/simular")
    public String processarSimulacao(@RequestParam BigDecimal aporte, 
                                     @RequestParam int meses, 
                                     @RequestParam String ativo, 
                                     Model model) {
    	
    	BigDecimal selicReal = investimentoService.getTaxaSelicAtual().multiply(new BigDecimal("100"));
        model.addAttribute("selicReal", selicReal);
        
        try {
            var cotacoes = moedaClient.buscarCotacoesAtuais();
            model.addAttribute("taxaUSD", cotacoes.get("USD"));
            model.addAttribute("taxaEUR", cotacoes.get("EUR"));
        } catch (Exception e) {
            model.addAttribute("taxaUSD", 5.05);
            model.addAttribute("taxaEUR", 5.42);
        }
    	
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