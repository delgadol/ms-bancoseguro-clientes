package com.bancoseguro.msclientes.domain.repositories;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bancoseguro.msclientes.domain.models.Cliente;
import com.bancoseguro.msclientes.utils.TipoDocumento;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientesRepository extends ReactiveMongoRepository<Cliente, String>{
	
	 Flux<Cliente> findAllByIndEliminado(int indEliminado);	
	 
	 Mono<Long> countByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento,String numDocumento);
	 
	 Mono<Cliente> findFirstByTipoDocumentoAndNumDocumento(TipoDocumento tipoDocumento,String numDocumento);
	 
	 Mono<Cliente> findFirstByIdAndIndEliminado(String id,Integer indEliminado);
	 
	 Mono<Long> countByIdAndIndEliminado(String id,Integer indEliminado);

}
