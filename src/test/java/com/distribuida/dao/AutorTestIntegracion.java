package com.distribuida.dao;

import com.distribuida.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AutorTestIntegracion {

    @Autowired
    private AutorDao autorDao;

    @Test
    public void testGuardarAutor() {
        Autor autor = new Autor(null, "Gabriel", "García Márquez", "Colombiana");
        Autor guardado = autorDao.save(autor);

        assertNotNull(guardado.getIdAutor());
    }

    @Test
    public void testBuscarPorId() {
        Autor autor = autorDao.save(new Autor(null, "Julio", "Cortázar", "Argentina"));

        Optional<Autor> encontrado = autorDao.findById(autor.getIdAutor());
        assertTrue(encontrado.isPresent());
        assertEquals("Julio", encontrado.get().getNombre());
    }

    @Test
    public void testListarAutores() {
        autorDao.save(new Autor(null, "Mario", "Vargas Llosa", "Peruana"));
        autorDao.save(new Autor(null, "Isabel", "Allende", "Chilena"));

        List<Autor> lista = autorDao.findAll();
        assertTrue(lista.size() >= 2);
    }

    @Test
    public void testEliminarAutor() {
        Autor autor = autorDao.save(new Autor(null, "Felipe", "Pineda", "Ecuatoriano"));
        autorDao.deleteById(autor.getIdAutor());

        assertFalse(autorDao.findById(autor.getIdAutor()).isPresent());
    }
}
