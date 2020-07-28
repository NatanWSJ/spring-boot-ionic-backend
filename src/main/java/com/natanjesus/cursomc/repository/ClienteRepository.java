package com.natanjesus.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.natanjesus.cursomc.domain.Cliente;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly=true)
    boolean existsByEmail(String email);

    @Transactional(readOnly=true)
    Cliente findByEmail(String email);
	
}
