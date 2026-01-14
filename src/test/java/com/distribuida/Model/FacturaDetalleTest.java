package com.distribuida.Model;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTest {

    private FacturaDetalle detalle;
    private Libro libro;
    private Factura factura;

    @BeforeEach
    public void setup() {

        libro = new Libro(1, "El Hobbit", 19.99, 10, null);
        factura = new Factura(1, "FAC-0001", new java.util.Date(), 100.0, 12.0, 112.0, null);

        detalle = new FacturaDetalle();
        detalle.setIdDetalle(1);
        detalle.setCantidad(2);
        detalle.setSubtotal(39.98);

        detalle.setLibro(libro);
        detalle.setFactura(factura);
    }

    @Test
    public void testDetalleConstructor() {
        assertAll("Validar constructor Detalle",
                () -> assertEquals(1, detalle.getIdDetalle()),
                () -> assertEquals(2, detalle.getCantidad()),
                () -> assertEquals(39.98, detalle.getSubtotal())
        );
    }

    @Test
    public void testDetalleSetters() {
        libro = new Libro(2, "1984", 15.50, 5, null);
        factura = new Factura(2, "FAC-0002", new java.util.Date(), 200.0, 24.0, 224.0, null);

        detalle = new FacturaDetalle();
        detalle.setIdDetalle(2);
        detalle.setCantidad(3);
        detalle.setSubtotal(46.50);
        detalle.setLibro(libro);
        detalle.setFactura(factura);

        assertAll("Validar setters Detalle",
                () -> assertEquals(2, detalle.getIdDetalle()),
                () -> assertEquals(3, detalle.getCantidad()),
                () -> assertEquals(46.50, detalle.getSubtotal()),
                () -> assertEquals("1984", detalle.getLibro().getTitulo()),
                () -> assertEquals("FAC-0002", detalle.getFactura().getNumFactura())
        );
    }

    @Test
    public void testDetalleToString() {
        String str = detalle.toString();

        assertAll("Validar toString Detalle",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("39.98")),
                () -> assertTrue(str.contains("El Hobbit"))
        );
    }
}
