package com.distribuida.service;

import com.distribuida.dao.FacturaDao;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImpl implements FacturaService{

    @Autowired
    private FacturaDao facturaDao;



    @Override
    public List<Factura> findAll() {
        return facturaDao.findAll();
    }

    @Override
    public Optional<Factura> findOne(int id) {
        return facturaDao.findById(id);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaDao.save(factura);
    }

    @Override
    public Factura update(int id, Factura factura) {
        Optional<Factura> facturaExistente = facturaDao.findById(id);

        if (facturaExistente == null){
            return null;
        }

        facturaExistente.orElse(null).setNumFactura(factura.getNumFactura());
        facturaExistente.orElse(null).setFecha(factura.getFecha());
        facturaExistente.orElse(null).setTotalNeto(factura.getTotalNeto());
        facturaExistente.orElse(null).setIva(factura.getIva());
        facturaExistente.orElse(null).setTotal(factura.getTotal());
        facturaExistente.orElse(null).setCliente(factura.getCliente());

        return facturaDao.save(facturaExistente.orElse(null));
    }

    @Override
    public void delete(int id) {
        if (facturaDao.existsById(id)){
            facturaDao.deleteById(id);
        }
    }
}
