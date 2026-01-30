package com.distribuida.dao;

import com.distribuida.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaDao extends JpaRepository<Factura, Integer> {
    //Optional<Factura> findAllById(int i);
}
