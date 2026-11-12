package com.dws.examen.exceptions;

public class CodigoSkuAlreadyExistsException extends RuntimeException {
    public CodigoSkuAlreadyExistsException(String mensaje) {
        super(mensaje);
        
    }
}