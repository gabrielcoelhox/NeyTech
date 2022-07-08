package com.neytech.mscards.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neytech.mscards.entities.ClientCard;
import com.neytech.mscards.repository.ClientCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientCardService {

	private final ClientCardRepository repository;
	
	public List<ClientCard> listCardsByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}
}
