package com.finedu.api.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finedu.api.domain.model.LancamentoFinanceiro;
import com.finedu.api.domain.repository.LancamentoRepository;

@Service
public class LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    
    public LancamentoService(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Transactional
    public LancamentoFinanceiro registrarNovoLancamento(LancamentoFinanceiro lancamento) {
        validarDataLancamento(lancamento.getDataLancamento());
        normalizarDescricao(lancamento);
        return lancamentoRepository.save(lancamento);
    }

    private void validarDataLancamento(LocalDate data) {
        if (data != null && data.isAfter(LocalDate.now())) {
            throw new RuntimeException("Não é permitido registrar lançamentos com data futura.");
        }
    }

    private void normalizarDescricao(LancamentoFinanceiro lancamento) {
        if (lancamento.getDescricao() != null) {
            lancamento.setDescricao(lancamento.getDescricao().trim().toUpperCase());
        }
    }

    public List<LancamentoFinanceiro> listarTodosOsLancamentos() {
        return lancamentoRepository.findAll();
    }

    @Transactional
    public void excluirLancamento(Long id) {
        lancamentoRepository.deleteById(id);
    }
}