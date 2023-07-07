package com.bancoseguro.msclientes.bussiness.services.impl;

import com.bancoseguro.msclientes.bussiness.services.ClienteService;
import com.bancoseguro.msclientes.domain.dto.req.ClienteModReq;
import com.bancoseguro.msclientes.domain.dto.req.ClienteReq;
import com.bancoseguro.msclientes.domain.dto.resp.ClienteRes;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ClienteServiceImpl implements ClienteService{

	@Override
	public Flux<ClienteRes> getClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ClienteRes> getClientById(String idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ClienteRes> postClient(ClienteReq cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ClienteRes> putClient(String idClient, ClienteModReq cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ClienteRes> putClientState(String idClient, String stateClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<ClienteRes> delClient(String idClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
