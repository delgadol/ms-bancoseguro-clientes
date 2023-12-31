package com.bancoseguro.msclientes.domain.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bancoseguro.msclientes.utils.TipoCliente;
import com.bancoseguro.msclientes.utils.TipoDocumento;

import lombok.Data;

/**
 * Representa un cliente.
 * La clase Cliente es una entidad que se mapea a la colección "personas" en la base de datos.
 */
@Data
@Document("personas")
public class Cliente  implements Serializable{
	
	private static final long serialVersionUID = 3816734689501931947L;

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
