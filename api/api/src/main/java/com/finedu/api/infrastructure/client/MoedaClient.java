package com.finedu.api.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.Map;

@Component
public class MoedaClient {
    private static final String URL = "https://api.hgbrasil.com/finance?fields=only_results,currencies&key=f1d293f0"; // Use sua chave se tiver

    public Map<String, BigDecimal> buscarCotacoesAtuais() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(URL, Map.class);
        
        Map<String, Object> results = (Map<String, Object>) response.get("results");
        if (results == null) throw new RuntimeException("Falha ao obter 'results' da API");

        Map<String, Object> currencies = (Map<String, Object>) results.get("currencies");
        
        Map<String, Object> usd = (Map<String, Object>) currencies.get("USD");
        Map<String, Object> eur = (Map<String, Object>) currencies.get("EUR");
        
        BigDecimal valUSD = new BigDecimal(usd.get("buy").toString());
        BigDecimal valEUR = new BigDecimal(eur.get("buy").toString());


        return Map.of("USD", valUSD, "EUR", valEUR);
    }}