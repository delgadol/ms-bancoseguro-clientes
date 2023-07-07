package com.bancoseguro.msclientes.domain.dto.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClienteModReq {
	
	@NotEmpty
	private String nombres;
	
	@NotEmpty
	private String apellidos;

}
