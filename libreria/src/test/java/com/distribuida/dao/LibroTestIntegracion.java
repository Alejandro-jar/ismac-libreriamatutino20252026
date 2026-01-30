package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LibroTestIntegracion {

    @Autowired
    private LibroDao libroDao;

    @Autowired
    private AutorDao autorDao;



    @Test
    public void testGuardarLibro() {
        Autor autor = autorDao.save(new Autor(null, "Gabriel", "García Márquez", "Colombiana"));

        Libro libro = new Libro(null, "Cien Años de Soledad", 20.50, 10, autor);
        Libro guardado = libroDao.save(libro);

        assertNotNull(guardado.getIdLibro());
    }

    @Test
    public void testBuscarPorId() {
        Autor autor = autorDao.save(new Autor(null, "Julio", "Cortázar", "Argentina"));

        Libro libro = libroDao.save(new Libro(null, "Rayuela", 18.00, 20, autor));

        Optional<Libro> encontrado = libroDao.findById(libro.getIdLibro());
        assertTrue(encontrado.isPresent());
        assertEquals("Rayuela", encontrado.get().getTitulo());
    }

    @Test
    public void testListarLibros() {
        autorDao.save(new Autor(null, "Mario", "Vargas Llosa", "Peruana"));

        libroDao.save(new Libro(null, "La Ciudad y los Perros", 15.00, 30,
                autorDao.findAll().get(0)));
    }

}
