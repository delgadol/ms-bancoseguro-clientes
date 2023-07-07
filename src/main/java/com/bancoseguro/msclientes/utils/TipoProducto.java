package com.bancoseguro.msclientes.utils;

public enum TipoProducto {
	
	CTA("Cuenta Bancaria"),
	CTA_AHORRO("Cuenta Ahorro"),
	CTA_CORRIENTE("Cuenta Corriente"),
	CTA_PLAZOFIJO("Cuenta Plazo Fijo"),	
	
	CRT("Credito"),
	CRT_EMPRESA("Credito Empresa"),
	CRT_PERSONA("Credito Persona"),
	
	CRT_TDC("Credito Tarjeta de Credito"),
	CRT_TDC_PERSONA("Tarjeta de Credito Persona"),
	CRT_TDC_EMPRESA("Tarjeta de Credito Empresa");
	

	private String descripcion;
	
	TipoProducto(String descProducto) {
		this.descripcion = descProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	
	
	
	

	

}
