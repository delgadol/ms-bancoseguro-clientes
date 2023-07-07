package com.bancoseguro.msclientes.domain.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bancoseguro.msclientes.domain.models.Cliente;

public interface ClientesRepository extends ReactiveMongoRepository<Cliente, String>{

}
