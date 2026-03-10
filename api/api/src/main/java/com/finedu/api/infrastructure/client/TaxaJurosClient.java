package com.finedu.api.infrastructure.client;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TaxaJurosClient {
private static final String URL_SELIC = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados/ultimos/1?formato=json";
    
    public BigDecimal buscarSelicAtual() {
        RestTemplate restTemplate = new RestTemplate();
      
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(URL_SELIC, HttpMethod.GET, entity, List.class);
        
        List<Map<String, String>> corpo = response.getBody();
        if (corpo != null && !corpo.isEmpty()) {
            String valor = corpo.get(0).get("valor");
            return new BigDecimal(valor).divide(new BigDecimal("100"));
        }
        return new BigDecimal("0.1075");
    }}