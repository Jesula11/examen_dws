package com.dws.examen.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.dws.examen.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepositoryImplementation<Producto, Long>{
	
	boolean existsByCodigoSku(String codigoSku);
    Optional<Producto> findByCodigoSku(String codigoSku);
    int countByCodigoSku(String codigoSku);
    void deleteByCodigoSku(String codigoSku);
}
