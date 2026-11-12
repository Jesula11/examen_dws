package com.dws.examen.dtos;

import java.time.LocalDate;

public record ProductoResponseDto(
		Long idProducto,
		String codigoSku,
		String nombre,
		String descripcion,
		Integer precio,
		Integer stock,
		LocalDate fechaAlta
		) {}




