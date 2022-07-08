package com.neytech.mscards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neytech.mscards.entities.ClientCard;

@Repository
public interface ClientCardRepository extends JpaRepository<ClientCard, Long>{

	List<ClientCard> findByCpf(String cpf);
}
