package com.finedu.api.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.TreeMap;

import org.springframework.stereotype.Service;

import com.finedu.api.domain.model.SimulacaoResultado;
import com.finedu.api.infrastructure.client.TaxaJurosClient;

@Service
public class InvestimentoService {

	private final TaxaJurosClient taxaJurosClient;

    public InvestimentoService(TaxaJurosClient taxaJurosClient) {
        this.taxaJurosClient = taxaJurosClient;
    }

    public SimulacaoResultado simular(BigDecimal aporteInicial, int meses, String ativo) {
        BigDecimal taxaMensal = buscarTaxaMensal(ativo);
        TreeMap<Integer, BigDecimal> evolucao = new TreeMap<>();
        BigDecimal montante = aporteInicial;

        evolucao.put(0, montante.setScale(2, RoundingMode.HALF_UP));


        for (int i = 1; i <= meses; i++) {
            montante = montante.multiply(BigDecimal.ONE.add(taxaMensal));
            evolucao.put(i, montante.setScale(2, RoundingMode.HALF_UP));
        }

        return new SimulacaoResultado(
            ativo, 
            montante.setScale(2, RoundingMode.HALF_UP), 
            montante.subtract(aporteInicial).setScale(2, RoundingMode.HALF_UP), 
            evolucao
        );
    }

    public BigDecimal getTaxaSelicAtual() {
        return taxaJurosClient.buscarSelicAtual();
    }

    private BigDecimal buscarTaxaMensal(String ativo) {
        BigDecimal taxaAnual = switch (ativo.toUpperCase()) {
            case "SELIC" -> getTaxaSelicAtual();
            case "CDB" -> getTaxaSelicAtual().subtract(new BigDecimal("0.0010"));
            default -> new BigDecimal("0.0617");
        };
        
        BigDecimal taxaMensal = taxaAnual.divide(new BigDecimal("12"), 8, RoundingMode.HALF_UP);
                
        return taxaMensal;
    }
}