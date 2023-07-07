package com.bancoseguro.msclientes.domain.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.bancoseguro.msclientes.utils.TipoCliente;
import com.bancoseguro.msclientes.utils.TipoDocumento;

import lombok.Data;

@Document("personas")
@Data
public class Cliente {
	
	private String id;
	
	private String secCtrl;
	
	private TipoCliente tipoCliente;
	
	private TipoDocumento tipoDocumento;
	
	private String idDocumento;
	
	private String nombres;
	
	private String apellidos;
	
	private String estado;
	
	private Integer indEliminado;
	

}
