package com.finedu.api.domain.model;

public record ArtigoEducativo(
    String titulo,
    String resumo,
    String icone,
    String detalheTecnico,
    String impactoFinanceiro
) {}