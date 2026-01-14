package com.distribuida.service;

import com.distribuida.dao.AutorDao;
import com.distribuida.model.Autor;
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
public class AutorServiceTestUnitario {

    @Mock
    private AutorDao autorDao;

    @InjectMocks
    private AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    void setUp() {
        autor = new Autor(1, "Gabriel", "Garcia Marquez");
    }

    @Test
    void findAll() {
        when(autorDao.findAll()).thenReturn(List.of(autor));

        assertEquals(1, autorService.findAll().size());
    }

    @Test
    void findOneExistente() {
        when(autorDao.findById(1)).thenReturn(Optional.of(autor));

        assertTrue(autorService.findOne(1).isPresent());
    }

    @Test
    void findOneNoExistente() {
        when(autorDao.findById(99)).thenReturn(Optional.empty());

        assertTrue(autorService.findOne(99).isEmpty());
    }

    @Test
    void save() {
        when(autorDao.save(autor)).thenReturn(autor);

        assertNotNull(autorService.save(autor));
    }

    @Test
    void updateNoExistente() {
        when(autorDao.existsById(99)).thenReturn(false);

        assertNull(autorService.update(99, autor));
    }
}
