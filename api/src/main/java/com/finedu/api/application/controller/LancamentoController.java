package com.finedu.api.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.finedu.api.domain.model.LancamentoFinanceiro;
import com.finedu.api.domain.model.TipoLancamento;
import com.finedu.api.domain.service.LancamentoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @GetMapping
    public String exibirPainel(Model model) {
        model.addAttribute("lancamentos", lancamentoService.listarTodosOsLancamentos());
        return "lancamentos/painel";
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("lancamento", new LancamentoFinanceiro());
        model.addAttribute("tipos", TipoLancamento.values());
        return "lancamentos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarNovoLancamento(@Valid @ModelAttribute("lancamento") LancamentoFinanceiro lancamento, 
                                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipos", TipoLancamento.values());
            return "lancamentos/formulario";
        }
        lancamentoService.registrarNovoLancamento(lancamento);
        return "redirect:/lancamentos";
    }
}