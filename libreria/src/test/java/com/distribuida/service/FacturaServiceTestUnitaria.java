package com.distribuida.service;

import com.distribuida.dao.FacturaDao;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaDao facturaDao;

    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente(
                1,
                "1701234567",
                "Juan",
                "Taipe",
                "Direccion",
                "0987654321",
                "jtaipe@correo.com"
        );

        factura = new Factura();
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);
    }

    @Test
    void findAll() {
        when(facturaDao.findAll()).thenReturn(List.of(factura));

        List<Factura> facturas = facturaService.findAll();

        assertNotNull(facturas);
        assertEquals(1, facturas.size());
        verify(facturaDao, times(1)).findAll();
    }

    @Test
    void findOneExistente() {
        when(facturaDao.findById(1)).thenReturn(Optional.of(factura));

        Optional<Factura> resultado = facturaService.findOne(1);

        assertTrue(resultado.isPresent());
        assertEquals("FAC-0001", resultado.get().getNumFactura());
    }

    @Test
    void findOneNoExistente() {
        when(facturaDao.findById(99)).thenReturn(Optional.empty());

        Optional<Factura> resultado = facturaService.findOne(99);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void save() {
        when(facturaDao.save(factura)).thenReturn(factura);

        Factura resultado = facturaService.save(factura);

        assertNotNull(resultado);
        assertEquals("FAC-0001", resultado.getNumFactura());
        verify(facturaDao).save(factura);
    }

    @Test
    void updateExistente() {
        Factura facturaActualizada = new Factura();
        facturaActualizada.setNumFactura("FAC-0002");
        facturaActualizada.setFecha(new Date());
        facturaActualizada.setTotalNeto(200.00);
        facturaActualizada.setIva(30.00);
        facturaActualizada.setTotal(230.00);
        facturaActualizada.setCliente(cliente);

        when(facturaDao.existsById(1)).thenReturn(true);
        when(facturaDao.save(any(Factura.class))).thenReturn(facturaActualizada);

        Factura resultado = facturaService.update(1, facturaActualizada);

        assertNotNull(resultado);
        assertEquals("FAC-0002", resultado.getNumFactura());
        verify(facturaDao).save(any(Factura.class));
    }

    @Test
    void updateNoExistente() {
        Factura nuevaFactura = new Factura();

        when(facturaDao.existsById(99)).thenReturn(false);

        Factura resultado = facturaService.update(99, nuevaFactura);

        assertNull(resultado);
        verify(facturaDao, never()).save(any());
    }

    @Test
    void delete() {
        facturaService.delete(1);

        verify(facturaDao, times(1)).deleteById(1);
    }
}
