package com.neytech.mscreditappraiser.services;

import java.util.List;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neytech.mscreditappraiser.clients.CardsPerCustomerResponse;
import com.neytech.mscreditappraiser.clients.CartoesResourceClient;
import com.neytech.mscreditappraiser.clients.CustomerResourceClient;
import com.neytech.mscreditappraiser.entities.CustomerData;
import com.neytech.mscreditappraiser.entities.CustomerSituation;
import com.neytech.mscreditappraiser.exceptions.CustomerDataNotFoundException;
import com.neytech.mscreditappraiser.exceptions.ErroComunicacaoMicroservicesException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

	private final CustomerResourceClient customerClient;
    private final CartoesResourceClient cartoesClient;

    public CustomerSituation getCustomerSituation(String cpf)
            throws CustomerDataNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<CustomerData> dadosCustomerResponse = customerClient.customerData(cpf);
            ResponseEntity<List<CardsPerCustomerResponse>> cardsResponse = cartoesClient.getCartoesByCliente(cpf);

            return CustomerSituation
                    .builder()
                    .customer(dadosCustomerResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(ex.getMessage(), status);
        }
    }
}
