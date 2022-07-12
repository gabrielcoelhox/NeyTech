package com.neytech.mscreditappraiser.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.neytech.mscreditappraiser.clients.CardsResourceClient;
import com.neytech.mscreditappraiser.clients.CustomerResourceClient;
import com.neytech.mscreditappraiser.entities.ApprovedCard;
import com.neytech.mscreditappraiser.entities.Card;
import com.neytech.mscreditappraiser.entities.CustomerCardResponse;
import com.neytech.mscreditappraiser.entities.CustomerData;
import com.neytech.mscreditappraiser.entities.CustomerEvaluationReturn;
import com.neytech.mscreditappraiser.entities.CustomerSituation;
import com.neytech.mscreditappraiser.exceptions.CustomerDataNotFoundException;
import com.neytech.mscreditappraiser.exceptions.ErroComunicacaoMicroservicesException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {

	private final CustomerResourceClient customerClient;
    private final CardsResourceClient cartoesClient;

    public CustomerSituation getCustomerSituation(String cpf)
            throws CustomerDataNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
            ResponseEntity<List<CustomerCardResponse>> cardsResponse = cartoesClient.getCartoesByCliente(cpf);

            return CustomerSituation
                    .builder()
                    .customer(customerDataResponse.getBody())
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
    
    public CustomerEvaluationReturn carryOutEvaluation(String cpf, Long income)
            throws CustomerDataNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<CustomerData> customerDataResponse = customerClient.customerData(cpf);
            ResponseEntity<List<Card>> cardsResponse = cartoesClient.getMaxIncomeCard(income);

            List<Card> cards = cardsResponse.getBody();
            var approvedCardsList = cards.stream().map(card -> {

                CustomerData customerData = customerDataResponse.getBody();

                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal idadeBD = BigDecimal.valueOf(customerData.getAge());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = fator.multiply(basicLimit);

                ApprovedCard approved = new ApprovedCard();
                approved.setCard(card.getName());
                approved.setFlag(card.getFlag());
                approved.setApprovedLimit(approvedLimit);

                return approved;
            }).collect(Collectors.toList());
            return new CustomerEvaluationReturn(approvedCardsList);

        } catch (FeignException.FeignClientException ex) {
            int status = ex.status();
            if(HttpStatus.NOT_FOUND.value() == status) {
                throw new CustomerDataNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(ex.getMessage(), status);
        }
    }
}
