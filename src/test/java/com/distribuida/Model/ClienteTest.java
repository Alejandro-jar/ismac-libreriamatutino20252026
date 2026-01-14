package com.distribuida.Model;

import com.distribuida.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente(1,
                "1726692310",
                "Victor",
                "Guiña",
                "Calle callada",
                "0996401766",
                "correo@correo.com");
    }

    @Test
    public void testClienteConstructor(){

        assertAll("Validar datos del cliente con constructor",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1726692310", cliente.getCedula()),
                () -> assertEquals("Victor", cliente.getNombre()),
                () -> assertEquals("Guiña", cliente.getApellido()),
                () -> assertEquals("Calle callada", cliente.getDireccion()),
                () -> assertEquals("0996401766", cliente.getTelefono()),
                () -> assertEquals("correo@correo.com", cliente.getCorreo())
        );
    }

    @Test
    public void testClienteSetters(){

        cliente.setIdCliente(2);
        cliente.setCedula("1700000552");
        cliente.setNombre("Tomas");
        cliente.setApellido("Rojas");
        cliente.setDireccion("calle2");
        cliente.setTelefono("0995558882");
        cliente.setCorreo("correo@correo2");

        assertAll("Validar datos del cliente con setters",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("1700000552", cliente.getCedula()),
        () -> assertEquals("Tomas", cliente.getNombre()),
        () -> assertEquals("Rojas", cliente.getApellido()),
        () -> assertEquals("calle2", cliente.getDireccion()),
                () -> assertEquals("0995558882", cliente.getTelefono()),
                () -> assertEquals("correo@correo2", cliente.getCorreo())
        );
    }
    @Test
    public void testClienteToString(){

        String str = cliente.toString();
        assertAll("Validar datos del cliente en toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1726692310")),
                () -> assertTrue(str.contains("Victor")),
                () -> assertTrue(str.contains("Guiña")),
                () -> assertTrue(str.contains("Calle callada")),
                () -> assertTrue(str.contains("0996401766")),
                () -> assertTrue(str.contains("correo@correo.com"))
                );
    }


}
