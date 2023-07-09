package com.bancoseguro.msclientes.domain.dto.resp;

import com.bancoseguro.msclientes.utils.TipoCliente;

import lombok.Data;

@Data
public class ClienteRes {
	

	private String id;
	
	private String nombres;
	
	private String apellidos;
	
	private String estado;
	
	private TipoCliente tipoCliente;
	

}
