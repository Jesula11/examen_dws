package com.dws.examen.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.examen.dtos.ProductoRequestDto;
import com.dws.examen.dtos.ProductoResponseDto;
import com.dws.examen.service.ProductoService;



@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    // READ ALL - GET /api/producto
    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(productoService.obtenerTodos()); // HTTP 200 OK
    }
    
    // CREATE - POST /api/producto
    @PostMapping
    public ResponseEntity<ProductoResponseDto> crear(@RequestBody ProductoRequestDto requestDto) {
        ProductoResponseDto creado = productoService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }
    
    // UPDATE - PUT /api/producto/{codigoSku}
    @PutMapping("/codigoSku/{codigoSku}")
    public ResponseEntity<ProductoResponseDto> actualizar(
            @PathVariable String codigoSku,
            @RequestBody ProductoRequestDto requestDto) {
        ProductoResponseDto actualizado = productoService.actualizar(codigoSku, requestDto);
        return ResponseEntity.ok(actualizado);
    }
    
    // DELETE - DELETE /api/producto/{codigoSku}
    @DeleteMapping("/codigoSku/{codigoSku}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigoSkui) {
        productoService.eliminar();
        return ResponseEntity.noContent().build();
    }
}
