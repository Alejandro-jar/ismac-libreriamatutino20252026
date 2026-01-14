package com.distribuida.service;

import com.distribuida.dao.ClienteDao;
import com.distribuida.model.Cliente;
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
public class ClienteServiceTestUnitaria {

    @Mock
    private ClienteDao clienteDao;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "1701234567", "Juan", "Taipe",
                "Direccion", "0987654321", "juan@correo.com");
    }

    @Test
    void findAll() {
        when(clienteDao.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    void findOneExistente() {
        when(clienteDao.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente> resultado = clienteService.findOne(1);

        assertTrue(resultado.isPresent());
    }

    @Test
    void findOneNoExistente() {
        when(clienteDao.findById(99)).thenReturn(Optional.empty());

        Optional<Cliente> resultado = clienteService.findOne(99);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void save() {
        when(clienteDao.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
    }

    @Test
    void updateExistente() {
        when(clienteDao.existsById(1)).thenReturn(true);
        when(clienteDao.save(any())).thenReturn(cliente);

        Cliente resultado = clienteService.update(1, cliente);

        assertNotNull(resultado);
    }

    @Test
    void updateNoExistente() {
        when(clienteDao.existsById(99)).thenReturn(false);

        Cliente resultado = clienteService.update(99, cliente);

        assertNull(resultado);
    }

    @Test
    void delete() {
        clienteService.delete(1);
        verify(clienteDao).deleteById(1);
    }
}
