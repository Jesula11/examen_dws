package com.dws.examen.exceptions;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
    @ExceptionHandler(ProductoNotFoundException.class)
    public ProblemDetail manejarProductoNoEncontrado(ProductoNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("Producto no Encontrado");
        problem.setType(URI.create("https://api.com.dws.examen/errors/not-found"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(CodigoSkuAlreadyExistsException.class)
    public ProblemDetail manejarCodigoSkuDuplicado(CodigoSkuAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problem.setTitle("Conflicto de Datos");
        problem.setType(URI.create("https://api.com.dws.examen/errors/conflict"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

}
