package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FacturaDetalleTestIntegracion {

    @Autowired
    private AutorDao autorDao;

    @Autowired
    private LibroDao libroDao;

    @Autowired
    private FacturaDao facturaDao;

    @Autowired
    private FacturaDetalleDao facturaDetalleDao;

    @Test
    public void testGuardarDetalle() {

        Autor autor = autorDao.save(
                new Autor(null, "Gabriel", "García Márquez", "Colombiana")
        );

        Libro libro = libroDao.save(
                new Libro(null, "El Coronel No Tiene Quien Le Escriba", 12.00, 10, autor)
        );

        Factura factura = facturaDao.save(
                new Factura(1, "FAC-0001", new Date(), 19.99, 10.0, null, null)
        );

        FacturaDetalle detalle = new FacturaDetalle(
                null, 2, 24.00, libro, factura
        );

        FacturaDetalle guardado = facturaDetalleDao.save(detalle);

        assertNotNull(guardado.getIdDetalle());
    }

    @Test
    public void testBuscarPorId() {

        Autor autor = autorDao.save(
                new Autor(null, "Julio", "Cortázar", "Argentina")
        );

        Libro libro = libroDao.save(
                new Libro(null, "Bestiario", 10.00, 5, autor)
        );

        Factura factura = facturaDao.save(
                new Factura(2, "FAC-0002", new Date(), 80.0, 9.6, 89.6, null)
        );

        FacturaDetalle detalleGuardado = facturaDetalleDao.save(
                new FacturaDetalle(null, 3, 30.00, libro, factura)
        );

        Optional<FacturaDetalle> encontrado =
                facturaDetalleDao.findById(detalleGuardado.getIdDetalle());

        assertTrue(encontrado.isPresent());
        assertEquals(3, encontrado.get().getCantidad());
    }

}
