package com.bancoseguro.msclientes.domain.dto.req;

import com.bancoseguro.msclientes.utils.TipoDocumento;

import jakarta.validation.constraints.NotEmpty;

import com.bancoseguro.msclientes.utils.TipoCliente;

import lombok.Data;

@Data
public class ClienteReq {
	
	@NotEmpty
	private TipoCliente tipoCliente;
	
	@NotEmpty
	private TipoDocumento tipoDocumento;
	
	@NotEmpty
	private String idDocumento;
	
	@NotEmpty
    private String nombres;
	
	
	@NotEmpty
    private String apellidos;
	
	

}
