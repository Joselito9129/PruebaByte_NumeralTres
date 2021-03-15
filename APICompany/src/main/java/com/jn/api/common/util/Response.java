package com.jn.api.common.util;

public enum Response {

	SUCCESS_RESPONSE("0000", "Operacion realizada exitosamente", "SUCCESS"),
	ERROR_TRY("0001", "Ocurrio un error inesperado", "ERROR"),
	COMPANY_NOT_NULL("0002", "Empresa no puede estar vacia", "ERROR"),
	STATUS_NOT_EMPTY("0003", "El estado es obligatorio", "ERROR"),
	NAME_NOT_EMPTY("0004", "El nombre es obligatorio", "ERROR"),
	NIT_NOT_EMPTY("0005", "El nit es obligatorio", "ERROR"),
	ADDRESS_NOT_EMPTY("0006", "La direccion es obligatoria", "ERROR"),
	FOUNDED_NOT_EMPTY("0007", "La fecha de funcacion es obligatoria", "ERROR"),
	COMPANY_EXISTS("0008", "Empresa ya se encuentra registrada", "ERROR"),
	COMPANY_NOT_EXISTS("0009", "Empresa no encontrada", "ERROR"),
	ID_NOT_EMPTY("0010", "Identificador unico es obligatorio", "ERROR"),
	STATUS_INCOMPATIBLE("0011", "Empresa se encuentra en un estado incompatible", "ERROR"),
	USER_NOT_EMPTY("0012", "Nombre de usuario es requerido", "ERROR");

	private String code;
	private String message;
	private String indicator;

	private Response(String code, String message, String indicator) {
		this.code = code;
		this.message = message;
		this.indicator = indicator;
	}

	private Response(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getIndicator() {
		return indicator;
	}
}
