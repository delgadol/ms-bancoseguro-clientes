package com.bancoseguro.msclientes.bussiness.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoseguro.msclientes.bussiness.services.ClienteService;
import com.bancoseguro.msclientes.domain.dto.req.ClienteModReq;
import com.bancoseguro.msclientes.domain.dto.req.ClienteReq;
import com.bancoseguro.msclientes.domain.dto.resp.ClienteRes;
import com.bancoseguro.msclientes.domain.models.Cliente;
import com.bancoseguro.msclientes.domain.repositories.ClientesRepository;
import com.bancoseguro.msclientes.utils.BankFnUtils;
import com.bancoseguro.msclientes.utils.ModelMapperUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClientesRepository servRepo;

	@Override
	public Flux<ClienteRes> getClients() {
		Flux<Cliente> sourceFlux = servRepo.findAll();
        return ModelMapperUtils.mapToFlux(sourceFlux, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> getClientById(String idClient) {
		Mono<Cliente> sourceMono = servRepo.findById(idClient);
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> postClient(ClienteReq cliente) {
		Cliente nuevoCliente = ModelMapperUtils.map(cliente, Cliente.class);
		nuevoCliente.setSecCtrl(BankFnUtils.uniqueProductCode());
		nuevoCliente.setEstado("0");
		nuevoCliente.setIndEliminado(0);
		Mono<Cliente> sourceMono = servRepo.save(nuevoCliente);
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> putClient(String idClient, ClienteModReq cliente) {
		Mono<Cliente> sourceMono = servRepo.findById(idClient);		
		Mono<Cliente> clienteModificado = sourceMono.flatMap(original->{
			boolean esModificado = false;
			Cliente nuevoCliente = new Cliente();
			nuevoCliente = ModelMapperUtils.map(original, Cliente.class);
			if (!nuevoCliente.getNombres().equalsIgnoreCase(cliente.getNombres())) {
				nuevoCliente.setNombres(cliente.getNombres());
				esModificado = true;
			}
			if (!nuevoCliente.getApellidos().equalsIgnoreCase(cliente.getApellidos())) {
				nuevoCliente.setApellidos(cliente.getApellidos());
				esModificado = true;
			}
			if (esModificado) {
				return Mono.just(nuevoCliente);
			}else {
				return null;
			}
		});
		Mono<Cliente> sourceModificado = clienteModificado.flatMap(modificado->{
			return servRepo.save(modificado);
		});
		return ModelMapperUtils.mapToMono(sourceModificado, ClienteRes.class);				
	}

	@Override
	public Mono<ClienteRes> putClientState(String idClient, String stateClient) {
		return null;
	}

	@Override
	public Mono<ClienteRes> delClient(String idClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
