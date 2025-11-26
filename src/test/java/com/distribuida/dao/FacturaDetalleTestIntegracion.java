/*package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class FacturaDetalleTestIntegracion {

    @Autowired
    private FacturaDetalle detalle;

    @Autowired
    private Libro libro;

    @Autowired
    private AutorDao autorDao;

    @Autowired
    private Factura factura;

    @Test
    public void testGuardarDetalle() {
        Autor autor = autorDao.save(new Autor(null, "Gabriel", "García Márquez", "Colombiana"));

        Libro libro = libro.save(new Libro(null, "El Coronel No Tiene Quien Le Escriba", 12.00, 10, autor));

        Factura factura = factura.save(new Factura(null, "FAC-0001", new Date(), 50.0, 6.0, 56.0, null));

        FacturaDetalle detalle = new FacturaDetalle(null, 2, 24.00, libro, factura);

        FacturaDetalle guardado = detalle.save(detalle);

        assertNotNull(guardado.getIdDetalle());
    }

    @Test
    public void testBuscarPorId() {
        Autor autor = autor.save(new Autor(null, "Julio", "Cortázar", "Argentina"));
        Libro libro = libro.save(new Libro(null, "Bestiario", 10.00, 5, autor, cat));

        Factura factura = factura.save(new Factura(null, "FAC-0002", new Date(), 80.0, 9.6, 89.6, null));
        FacturaDetalle detalle = detalle.save(new FacturaDetalle(null, 3, 30.00, libro, factura));

        Optional<FacturaDetalle> encontrado = detalle.findById(detalle.getIdDetalle());

        assertTrue(encontrado.isPresent());
        assertEquals(3, encontrado.get().getCantidad());
    }
*/

