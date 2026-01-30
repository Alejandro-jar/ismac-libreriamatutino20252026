package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleDao;
import com.distribuida.model.FacturaDetalle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaDetalleServiceTestUnitaria {

    @Mock
    private FacturaDetalleDao facturaDetalleDao;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle detalle;

    @BeforeEach
    void setUp() {
        detalle = new FacturaDetalle();
        detalle.setIdDetalle(1);
        detalle.setCantidad(2);
        detalle.setPrecio(10.0);
        detalle.setSubtotal(20.0);
    }

    @Test
    void findAll() {
        when(facturaDetalleDao.findAll()).thenReturn(List.of(detalle));

        assertEquals(1, facturaDetalleService.findAll().size());
    }

    @Test
    void save() {
        when(facturaDetalleDao.save(detalle)).thenReturn(detalle);

        assertNotNull(facturaDetalleService.save(detalle));
    }

    @Test
    void delete() {
        facturaDetalleService.delete(1);
        verify(facturaDetalleDao).deleteById(1);
    }
}
