package com.neytech.mscards.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neytech.mscards.entities.Card;
import com.neytech.mscards.entities.ClientCard;
import com.neytech.mscards.entities.DataCardApplicationEmission;
import com.neytech.mscards.repository.CardRepository;
import com.neytech.mscards.repository.ClientCardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SubscriberCardIssue {

	private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;

    @RabbitListener(queues = "${mq.queues.card-issuance}")
    public void receberSolicitacaoEmissao(@Payload String payload){
        try {
            var mapper = new ObjectMapper();

            DataCardApplicationEmission data = mapper.readValue(payload, DataCardApplicationEmission.class);
            Card card = cardRepository.findById(data.getIdCard()).orElseThrow();

            ClientCard customerCard = new ClientCard();
            customerCard.setCard(card);
            customerCard.setCpf(data.getCpf());
            customerCard.setLimit(data.getLimitReleased());

            clientCardRepository.save(customerCard);
        } catch (Exception ex) {
            log.error("Error when receiving card issuance request: {}", ex.getMessage());
        }
    }
}
