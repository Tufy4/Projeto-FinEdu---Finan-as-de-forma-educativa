package com.finedu.api.domain.service;

import com.finedu.api.domain.model.ArtigoEducativo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EducacaoService {

    public List<ArtigoEducativo> listarArtigosPrincipais() {
        return List.of(
            new ArtigoEducativo(
                "SELIC: A Taxa Mãe",
                "É a taxa básica de juros da economia brasileira, definida pelo COPOM.",
                "bi-bank",
                "Influencia todas as outras taxas de juros no país, como empréstimos e investimentos.",
                "Quando sobe, investimentos em Renda Fixa rendem mais e o consumo tende a cair."
            ),
            new ArtigoEducativo(
                "CDI: O Benchmark dos Bancos",
                "Certificado de Depósito Interbancário. É quanto os bancos emprestam entre si.",
                "bi-arrow-left-right",
                "Anda sempre colado na SELIC (geralmente 0,10% abaixo).",
                "É a régua principal para medir se o seu CDB ou conta digital é um bom negócio."
            ),
            new ArtigoEducativo(
                "CDB: Emprestando para o Banco",
                "Título de renda fixa emitido por bancos para captar recursos.",
                "bi-cash-coin",
                "Possui proteção do FGC (Fundo Garantidor de Créditos) até R$ 250 mil.",
                "Geralmente oferece rentabilidades superiores à poupança, mantendo segurança similar."
            )
        );
    }
}