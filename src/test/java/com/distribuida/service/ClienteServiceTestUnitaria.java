package com.distribuida.service;

import com.distribuida.dao.ClienteDao;
import com.distribuida.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTestUnitaria {

    @Mock
    private clienteDao

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setCedula("171234567");
        cliente.setNombre("Juan");
        cliente.setApellido("Taipe");
        cliente.setDireccion("Av. por ahi y mas alla");
        cliente.setTelefono("0987654321");
        cliente.setCorreo("jtaipe@correo.com");
    }

    @Test
    public void findAll(){
        when(clienteDao.findAll()).thenReturn(List.of(cliente));
        List<Cliente> clientes = clienteService.findAll();

        assertNotNull(clientes);
        assertEquals(1,clientes.size());
        verify(clienteDao, times(1).findAll());
    }

    @Test
    public void testFindOneExistente(){
        when(clienteDao.findById(1)).thenReturn(Optional.ofNullable(cliente));
        Optional<Cliente> cliente = clienteService.findOne(1);

        assertNotNull(cliente);
        assertEquals("Juan", cliente.orElse(null).getNombre());
    }

    @Test
    public void testFindOneNoExistente(){
        when(clienteDao.findById(2)).thenReturn(null;
        Cliente cliente = clienteService.findOne(2);
        assertNull(cliente);
    }

    @Test
    public void testSave(){
        when(clienteDao.save(cliente)).thenReturn(cliente);
        Cliente clienteGuardado = clienteService.save(cliente);
        assertNotNull(clienteGuardado);
        assertEquals("Juan", clienteGuardado.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCedula("17012345666");
        clienteActualizado.setNombre("Juan66");
        clienteActualizado.setApellido("Taipe66");
        clienteActualizado.setDireccion("Direccion66");
        clienteActualizado.setTelefono("0987654666");
        clienteActualizado.setCorreo("jtaipe66@correo.com");

        when(clienteDao.fundById(1)).thenReturn(Optional.ofNullable(cliente));
        when(clienteDao.save(any())).thenReturn(clienteActualizado);

        Cliente clienteResultado = clienteService.update(1, clienteActualizado);

        assertNotNull(clienteResultado);
        assertEquals("Juan66", clienteResultado.getNombre());
        verify(clienteDao, Times(1)).save(cliente);
    }

    @Test
    public void testUpdateNoExistente(){
        Cliente clienteNuevo = new Cliente();
        when(clienteDao.findById(999)).thenReturn(Optional.empty());
        Cliente resultado = clienteService.update(999, clienteNuevo);

        assertNull(resultado);
        verify(clienteDao,never()).save(any());

    }

    @Test
    public void testDeleteEcistente(){
        when(clienteDao.existById(1)).thenReturn(true);
        clienteService.delete(1);
        verify(clienteDao).deleteById(1);
    }

     @Test
    public void testDeleteNoExistente(){
        when(clienteDao.existById(999)).thenReturn(false);
        clienteService.delete(999);
        verify(clienteDao, never()).deleteById(anyInt());
    }


}

