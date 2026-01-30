package com.distribuida.dao;

import com.distribuida.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroDao extends JpaRepository<Libro, Integer> {
}
