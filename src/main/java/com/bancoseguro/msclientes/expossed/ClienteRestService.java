package com.bancoseguro.msclientes.expossed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bancoseguro.msclientes.bussiness.services.ClienteService;
import com.bancoseguro.msclientes.domain.dto.req.ClienteModReq;
import com.bancoseguro.msclientes.domain.dto.req.ClienteReq;
import com.bancoseguro.msclientes.domain.dto.resp.ClienteRes;
import com.bancoseguro.msclientes.utils.ApiHelperPath;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import jakarta.validation.Valid;


@RestController
@Validated
@RequestMapping(ApiHelperPath.API_CLIENTES_BASE)
public class ClienteRestService {
	
	@Autowired
	private ClienteService clientService; 
	
	@GetMapping("")
	public Flux<ClienteRes> getClients(){
		return clientService.getClients();
	}
	
	
	@GetMapping("/{id}")
	public Mono<ClienteRes> getClientById(@PathVariable(name = "id") String idClient){
		return clientService.getClientById(idClient).
				switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Entidad no procesable")));
	}
	
	
	@PutMapping("/{id}")
	public Mono<ClienteRes> putClient(@PathVariable(name="id") String idClient,@Valid @RequestBody ClienteModReq cliente){
		return clientService.putClient(idClient, cliente).
				switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Entidad no procesable")));
	}
	
	
	@PostMapping("")
	public Mono<ClienteRes> postClient(@Valid @RequestBody ClienteReq cliente){
		return clientService.postClient(cliente);
	}
	
	@DeleteMapping("/{id}")
	public Mono<ClienteRes> delClient(@PathVariable(name="id") String idClient){
		return null;
	}
	
	
	@PutMapping("/{id}/estado/{idEstado}")
	public Mono<ClienteRes> putClientState(@PathVariable(name="id") String idClient, @PathVariable(name="idEstado") String stateClient){
		return null;
	}
	

}
