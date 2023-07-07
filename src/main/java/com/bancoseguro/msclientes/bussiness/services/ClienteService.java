package com.bancoseguro.msclientes.bussiness.services;

import com.bancoseguro.msclientes.domain.dto.req.ClienteModReq;
import com.bancoseguro.msclientes.domain.dto.req.ClienteReq;
import com.bancoseguro.msclientes.domain.dto.resp.ClienteRes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClienteService {
	
	public Flux<ClienteRes> getClients();
	
	public Mono<ClienteRes> getClientById(String idClient);
	
	public Mono<ClienteRes> postClient(ClienteReq cliente);
	
	public Mono<ClienteRes> putClient(String idClient,ClienteModReq cliente);
	
	public Mono<ClienteRes> putClientState(String idClient,String stateClient);	
		
	public Mono<ClienteRes> delClient(String idClient);
	

}
