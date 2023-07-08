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
		//Flux<Cliente> sourceFlux = servRepo.findAll();
		Flux<Cliente> sourceFlux = servRepo.findAllByIndEliminado(1);
        return ModelMapperUtils.mapToFlux(sourceFlux, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> getClientById(String idClient) {
		Mono<Cliente> sourceMono = servRepo.findById(idClient);
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> postClient(ClienteReq cliente) {
		/**
		Mono<Cliente> clienteExistente = servRepo
				.findByTipoDocumentoAndNumDocumento(cliente.getTipoDocumento(),cliente.getNumDocumento());
		Cliente nuevoCliente = ModelMapperUtils.map(cliente, Cliente.class);
		nuevoCliente.setSecCtrl(BankFnUtils.uniqueProductCode());
		nuevoCliente.setEstado("0");
		nuevoCliente.setIndEliminado(0);
		Mono<Cliente> sourceMono = servRepo.save(nuevoCliente);
		// return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
		 * **/
		Mono<Object> sourceMono = servRepo.countByTipoDocumentoAndNumDocumento(cliente.getTipoDocumento(), cliente.getNumDocumento())
		.flatMap(t->{	
			Cliente nuevoCliente = ModelMapperUtils.map(cliente, Cliente.class);
			if(t == 0) {
				System.out.print("Valor " + t);				
				nuevoCliente.setSecCtrl(BankFnUtils.uniqueProductCode());
				nuevoCliente.setEstado("0");
				nuevoCliente.setIndEliminado(0);
				return servRepo.save(nuevoCliente);
			}
			return Mono.just(null);		
		});
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class).switchIfEmpty(Mono.just(null));
		//return 
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
		Mono<Cliente> sourceMono = servRepo.findById(idClient);		
		Mono<Cliente> clienteModificado = sourceMono.flatMap(original->{
			boolean esModificado = false;
			Cliente nuevoCliente = new Cliente();
			nuevoCliente = ModelMapperUtils.map(original, Cliente.class);
			if (!nuevoCliente.getEstado().equalsIgnoreCase(stateClient)) {
				nuevoCliente.setEstado(stateClient);
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
	public Mono<ClienteRes> delClient(String idClient) {
		// TODO Auto-generated method stub
		return null;
	}

}
