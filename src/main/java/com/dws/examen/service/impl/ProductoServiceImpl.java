package com.dws.examen.service.impl;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dws.examen.dtos.ProductoRequestDto;
import com.dws.examen.dtos.ProductoResponseDto;
import com.dws.examen.entities.Producto;
import com.dws.examen.exceptions.CodigoSkuAlreadyExistsException;
import com.dws.examen.exceptions.ProductoNotFoundException;
import com.dws.examen.repositories.ProductoRepository;
import com.dws.examen.service.ProductoService;



@Service
public class ProductoServiceImpl implements ProductoService {
	
	private final ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

	@Override
	@Transactional (readOnly = true)
	public List<ProductoResponseDto> obtenerTodos() {
		return productoRepository.findAll()
                .stream()
                .map(convertirProductoSeleccionado)
                .toList();
	}

	@Override
	@Transactional
	public ProductoResponseDto crear(ProductoRequestDto requestDto) {
		if (productoRepository.existsByCodigoSku(requestDto.codigoSku())) {
            throw new CodigoSkuAlreadyExistsException("Ya existe un producto registrado con el codigo: " + requestDto.codigoSku());
        }

        Producto nuevoProducto = new Producto(
                null,
                requestDto.codigoSku(),
                requestDto.nombre(),
                requestDto.descripcion(),
                requestDto.precio(),
                requestDto.stock(),
                requestDto.fechaAlta()
        );

        Producto guardado = productoRepository.save(nuevoProducto);
        return convertirProductoSeleccionado(guardado);
	}

	@Override
	@Transactional
	public ProductoResponseDto actualizar(String codigoSku, ProductoRequestDto requestDto) {
		Producto productoExistente = productoRepository.findByCodigoSku(codigoSku)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con el codigo: " + codigoSku));

        if (!productoExistente.getCodigoSku().equalsIgnoreCase(requestDto.codigoSku()) && productoRepository.existsByCodigoSku(requestDto.codigoSku())) {
            throw new CodigoSkuAlreadyExistsException("El codigo " + requestDto.codigoSku() + " ya está registrado en otro producto.");
        }

        productoExistente.setCodigoSku(requestDto.codigoSku());
        productoExistente.setNombre(requestDto.nombre());
        productoExistente.setDescripcion(requestDto.descripcion());
        productoExistente.setPrecio(requestDto.precio());
        productoExistente.setStock(requestDto.stock());
        productoExistente.setFechaAlta(requestDto.fechaAlta());

        Producto actualizado = productoRepository.save(productoExistente);
        return convertirProductoSeleccionado(actualizado);
    }

	@Override
	@Transactional
	public void eliminar(String codigoSku) {
		if (!productoRepository.existsByCodigoSku(codigoSku)) {
            throw new ProductoNotFoundException("No se puede eliminar. Producto no encontrado con el codigo: " + codigoSku);
        }
        productoRepository.deleteByCodigoSku(codigoSku);
    }
		
	
	
	private ProductoResponseDto convertirProductoSeleccionado(Producto productoSeleccionado) {
        return new ProductoResponseDto(
                productoSeleccionado.getIdProducto(),
                productoSeleccionado.getCodigoSku(),
                productoSeleccionado.getNombre(),
                productoSeleccionado.getDescripcion(),
                productoSeleccionado.getPrecio(),
                productoSeleccionado.getStock(),
                productoSeleccionado.getFechaAlta()
        );
    }
}
