package com.bancoseguro.msclientes.domain.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bancoseguro.msclientes.utils.TipoCliente;
import com.bancoseguro.msclientes.utils.TipoDocumento;

import lombok.Data;

@Data
@Document("personas")
public class Cliente {
	
	@Id
	private String id;
	
	private String secCtrl;
	
	private TipoCliente tipoCliente;
	
	private TipoDocumento tipoDocumento;
	
	private String numDocumento;
	
	private String nombres;
	
	private String apellidos;
	
	private String estado;
	
	private Integer indEliminado;
	

}
