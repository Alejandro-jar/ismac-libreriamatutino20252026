package com.distribuida.service;

import com.distribuida.dao.LibroDao;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTestUnitaria {

    @Mock
    private LibroDao libroDao;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;

    @BeforeEach
    void setUp() {
        libro = new Libro(1, "Java Avanzado", 50.0);
    }

    @Test
    void findAll() {
        when(libroDao.findAll()).thenReturn(List.of(libro));

        assertEquals(1, libroService.findAll().size());
    }

    @Test
    void findOneExistente() {
        when(libroDao.findById(1)).thenReturn(Optional.of(libro));

        assertTrue(libroService.findOne(1).isPresent());
    }

    @Test
    void save() {
        when(libroDao.save(libro)).thenReturn(libro);

        assertNotNull(libroService.save(libro));
    }
}
