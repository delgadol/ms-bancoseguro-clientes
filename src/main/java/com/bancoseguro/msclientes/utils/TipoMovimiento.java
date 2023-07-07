package com.bancoseguro.msclientes.utils;

public enum TipoMovimiento {
	
	CARGO("CAGOS"),
	ABONO("ABONOS");
	
	

	private String descripcion;
	
	TipoMovimiento(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	

}
