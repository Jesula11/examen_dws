package com.dws.examen.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name = "producto" , schema = "crm", uniqueConstraints = {
        @UniqueConstraint(name = "uk_producto_codigo_sku", columnNames = "codigo_sku")
})
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_prducto", nullable = false)
	private Long idProducto;
	
	@Column(name = "codigo_sku", nullable = false, length = 10, unique = true)
	private String codigoSku;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	@Column(name = "precio", nullable = false)
	private Integer precio;
	
	@Column(name = "stock", nullable = false)
	private Integer stock;
	
	@Column(name = "fecha_alta", nullable = false)
	private LocalDate fechaAlta;
	
	
	//Constructores
	public Producto() {
		super();
	}

	public Producto(Long idProducto, String codigoSku, String nombre, String descripcion, Integer precio, Integer stock,
			LocalDate fechaAlta) {
		super();
		this.idProducto = idProducto;
		this.codigoSku = codigoSku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fechaAlta = fechaAlta;
	}
	
	
	//Getters y Setters
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigoSku() {
		return codigoSku;
	}

	public void setCodigoSku(String codigoSku) {
		this.codigoSku = codigoSku;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}
