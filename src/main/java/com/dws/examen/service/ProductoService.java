package com.dws.examen.service;

import java.util.List;

import com.dws.examen.dtos.ProductoResponseDto;
import com.dws.examen.dtos.ProductoRequestDto;

public interface ProductoService {
	List<ProductoResponseDto> obtenerTodos();
	ProductoResponseDto crear(ProductoRequestDto requestDto);
	ProductoResponseDto actualizar(String codigoSku, ProductoRequestDto requestDto);
	void eliminar(String codigoSku);
}
