package com.neytech.mscreditappraiser.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neytech.mscreditappraiser.entities.DataCardApplicationEmission;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestEmissionCardPublisher {

	private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaoCartoes;
    
    public void requestCard(DataCardApplicationEmission data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
    }
    
    private String convertIntoJson(DataCardApplicationEmission data) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(data);
        return json;
    }
}
