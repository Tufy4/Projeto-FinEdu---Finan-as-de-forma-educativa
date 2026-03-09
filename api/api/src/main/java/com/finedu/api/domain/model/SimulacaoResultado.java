package com.finedu.api.domain.model;

import java.math.BigDecimal;
import java.util.Map;

public record SimulacaoResultado(
    String ativo,
    BigDecimal valorFinal,
    BigDecimal rendimentoTotal,
    Map<Integer, BigDecimal> evolucaoMensal
) {}