package com.account.management.client;


import com.account.management.dto.TransactionRequest;
import com.account.management.dto.TransactionResponse;
import com.account.management.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@Slf4j
@RequiredArgsConstructor
public class TransactionClient {

    private final RestTemplate restTemplate;




    public String addTransaction(TransactionRequest transactionRequest)  {

        log.info("Calling transaction service...");

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            URI uri = new URI(Constant.ACCOUNT_MANAGEMENT_BASE_URL + "/api/v1/transaction");

            RequestEntity<TransactionRequest> requestEntity = new RequestEntity<>(transactionRequest, headers, HttpMethod.POST, uri);
            ParameterizedTypeReference<TransactionResponse> parameterizedTypeReference = new ParameterizedTypeReference<>() {};
            ResponseEntity<TransactionResponse> responseEntity = restTemplate.exchange(requestEntity, parameterizedTypeReference);
            log.info("transaction response: " + responseEntity);

            return String.valueOf(responseEntity.getBody());


        } catch(Exception ex) {


            log.error(ex.getMessage());

            TransactionResponse transactionResponse = TransactionResponse.builder()
                    .id(0L)
                    .accountNumber("")
                    .createdAt("")
                    .depositAmount(null)
                    .name("")
                    .phone("")
                    .totalBalance(null)
                    .withdrawalAmount(null)
                    .build();

            return String.valueOf(ResponseEntity.status(500).body(transactionResponse));

        }


    }



}
