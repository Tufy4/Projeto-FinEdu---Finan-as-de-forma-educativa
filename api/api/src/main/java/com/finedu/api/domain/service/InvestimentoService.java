package com.finedu.api.domain.service;

import com.finedu.api.domain.model.SimulacaoResultado;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.TreeMap;

@Service
public class InvestimentoService {

    private static final BigDecimal TAXA_SELIC = new BigDecimal("0.1075"); // 10.75%
    private static final BigDecimal TAXA_CDI = new BigDecimal("0.1065");   // 10.65%
    private static final BigDecimal TAXA_POUPANCA = new BigDecimal("0.0617"); // 6.17%

    public SimulacaoResultado simular(BigDecimal aporteInicial, int meses, String ativo) {
        BigDecimal taxaMensal = buscarTaxaMensal(ativo);
        TreeMap<Integer, BigDecimal> evolucao = new TreeMap<>();
        BigDecimal montante = aporteInicial;

        for (int i = 0; i <= meses; i++) {
            evolucao.put(i, montante.setScale(2, RoundingMode.HALF_UP));
            montante = montante.multiply(BigDecimal.ONE.add(taxaMensal));
        }

        return new SimulacaoResultado(
        	    ativo, 
        	    montante.setScale(2, RoundingMode.HALF_UP), 
        	    montante.subtract(aporteInicial).setScale(2, RoundingMode.HALF_UP), 
        	    evolucao
        	);
    }

    private BigDecimal buscarTaxaMensal(String ativo) {
        BigDecimal taxaAnual = switch (ativo.toUpperCase()) {
            case "SELIC" -> TAXA_SELIC;
            case "CDB" -> TAXA_CDI;
            default -> TAXA_POUPANCA;
        };
        
        double mensal = Math.pow(1 + taxaAnual.doubleValue(), 1.0/12.0) - 1;
        return BigDecimal.valueOf(mensal);
    }
}