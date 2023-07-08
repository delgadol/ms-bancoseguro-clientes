package com.bancoseguro.msclientes.bussiness.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClientesRepository servRepo;

	@Override
	public Flux<ClienteRes> getClients() {
		//Flux<Cliente> sourceFlux = servRepo.findAll();
		Flux<Cliente> sourceFlux = servRepo.findAllByIndEliminado(0);
        return ModelMapperUtils.mapToFlux(sourceFlux, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> getClientById(String idClient) {
		Mono<Cliente> sourceMono = servRepo.findById(idClient);
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> postClient(ClienteReq cliente) {
		Mono<Long> clienteExistente = servRepo
				.countByTipoDocumentoAndNumDocumento(cliente.getTipoDocumento(),cliente.getNumDocumento());
		
		Mono<Cliente> sourceMono = clienteExistente
				.filter(x -> x== 0)
				.flatMap(t -> {
					Cliente nuevoCliente = ModelMapperUtils.map(cliente, Cliente.class);
					nuevoCliente.setSecCtrl(BankFnUtils.uniqueProductCode());
					nuevoCliente.setEstado("0");
					nuevoCliente.setIndEliminado(0);
					return servRepo.save(nuevoCliente);
				});	
		
		return ModelMapperUtils.mapToMono(sourceMono, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> putClient(String idClient, ClienteModReq cliente) {
		Mono<Cliente> clienteExistente = servRepo
				.findFirstByIdAndIndEliminado(idClient,0);
		
		Mono<Cliente> clienteModificado = clienteExistente
				.flatMap( original -> {
					Cliente nuevoCliente = new Cliente();
					nuevoCliente = ModelMapperUtils.map(original, Cliente.class);
					nuevoCliente.setNombres(cliente.getNombres());
					nuevoCliente.setApellidos(cliente.getApellidos());
					return servRepo.save(nuevoCliente);
				});
		
		return ModelMapperUtils.mapToMono(clienteModificado, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> putClientState(String idClient, String stateClient) {
		Mono<Cliente> clienteExistente = servRepo
				.findFirstByIdAndIndEliminado(idClient,0);
		
		Mono<Cliente> clienteModificado = clienteExistente
				.flatMap( original -> {
					Cliente nuevoCliente = new Cliente();
					nuevoCliente = ModelMapperUtils.map(original, Cliente.class);
					nuevoCliente.setEstado(stateClient);
					return servRepo.save(nuevoCliente);
				});
		
		return ModelMapperUtils.mapToMono(clienteModificado, ClienteRes.class);
	}

	@Override
	public Mono<ClienteRes> delClient(String idClient) {
		Mono<Cliente> clienteExistente = servRepo
				.findFirstByIdAndIndEliminado(idClient,0);
		
		Mono<Cliente> clienteModificado = clienteExistente
				.flatMap( original -> {
					Cliente nuevoCliente = new Cliente();
					nuevoCliente = ModelMapperUtils.map(original, Cliente.class);
					nuevoCliente.setIndEliminado(1);
					return servRepo.save(nuevoCliente);
				});
		
		return ModelMapperUtils.mapToMono(clienteModificado, ClienteRes.class);
	}

}
