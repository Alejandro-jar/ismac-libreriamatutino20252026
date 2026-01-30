package com.distribuida.Model;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup(){
            cliente = new Cliente(1
            , "1726692310"
            , "Tomas"
            ,"Rojas"
            , "calle callada"
            ,"0996401766"
            ,"correo@correo.com");

            factura = new Factura();

            factura.setIdFactura(1);
            factura.setNumFactura("FAC-0001");
            factura.setFecha(new Date());
            factura.setTotalNeto(100.00);
            factura.setIva(15.00);
            factura.setTotal(115.00);
                    // inyeccion de dependencias
            factura.setCliente(cliente);

    }

    @Test
    public void testFacturaConstructor() {
        assertAll("Validar datos de Constructor",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-0001", factura.getNumFactura()),
                //() -> assertEquals(1, factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal())
        );
    }


    @Test
        public void testFacturaSetters(){
        cliente = new Cliente(2,"123456789","Juan","Taipe","Direccion","0987654321","jtaipe@correo.com");
        factura = new Factura();
        factura.setIdFactura(2);
        factura.setNumFactura("FAC-0002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(30.00);
        factura.setTotal(230.00);
            //inyeccion de dependencias
        factura.setCliente(cliente);

        assertAll("Validad metodos setters - factura",
                () -> assertEquals(2, factura.getIdFactura()),
                () -> assertEquals("FAC-0002", factura.getNumFactura()),
                //() -> assertEquals(1, factura.getFecha()),
                () -> assertEquals(200.00, factura.getTotalNeto()),
                () -> assertEquals(30.00, factura.getIva()),
                () -> assertEquals(230.00, factura.getTotal()),
                () -> assertEquals("123456789", factura.getCliente().getCedula())
        );




        }

    @Test
    public void testFacturaToString(){
        String str = factura.toString();

        assertAll("Validar datos de toString - Factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Tomas"))
        );
    }

    @Test
    public void testFacturaInyeccion(){
        assertAll("Validar metido inyector",
                () -> assertNotNull(factura.getCliente()),
                () -> assertEquals("Rojas", factura.getCliente().getApellido())

                        );
    }

    @Test
    public void testFacturaValoresNegativos(){

        factura.setTotal(-100.00);
        assertAll(  "validar datos negativos - Factura",
                () -> assertEquals(  -100.00, factura.getTotal() )
    );
        // validar
    }


    }


