package com.distribuida.dao;

import com.distribuida.model.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class ClienteTestIntegracion {

    @Autowired
    public ClienteDao clienteDao;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteDao.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(Cliente item: clientes){
            System.out.println(item.toString());
        }
    }
    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteDao.findById(1);
        assertTrue(cliente.isPresent(), "El cliente con id = 1 si existe.");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        Cliente cliente = new Cliente(0, "123456789","juan","Taipe","Av. Cerca","0987654321", "correo@gmail.com");
        Cliente clienteGuardado = clienteDao.save(cliente);
        assertNotNull(clienteGuardado, "Cliente guardado correctamente");
        assertEquals("123456789", clienteGuardado.getCedula());
        assertEquals("juan", clienteGuardado.getNombre());
    }

    @Test
    public void update(){

        Optional<Cliente> cliente = clienteDao.findById(42);
        assertTrue(cliente.isPresent(), "El cliente existe en BD");
        cliente.orElse( null).setCedula("170123456777");
        cliente.orElse( null).setNombre("Juan77");
        cliente.orElse( null).setApellido("Taipe77");
        cliente.orElse( null).setDireccion("Direccion77");
        cliente.orElse( null).setTelefono("09876543277");
        cliente.orElse( null).setCorreo("jtaipE77@correo.com");

        Cliente clienteActualizado = clienteDao.save(cliente.orElse(null));
        assertNotNull(clienteActualizado, "Actualizacion completa");
        assertEquals("Juan77", clienteActualizado.getNombre());
        assertEquals("Taipe77", clienteActualizado.getApellido());
    }

    @Test
    public void delete() {
        if (clienteDao.existsById(41)) {
            clienteDao.deleteById(41);
        }
        assertFalse(clienteDao.existsById(40),"El dato fue eliminado");
    }

}
