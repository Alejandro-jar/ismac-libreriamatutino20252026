package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleDao;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleDao detalleDao;

    @Override
    public List<FacturaDetalle> findAll() {
        return detalleDao.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findOne(int id) {
        return detalleDao.findById(id);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle detalle) {
        return detalleDao.save(detalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle detalle) {

        Optional<FacturaDetalle> detExistente = detalleDao.findById(id);

        if (!detExistente.isPresent()) {
            return null;
        }

        FacturaDetalle d = detExistente.get();

        d.setCantidad(detalle.getCantidad());
        d.setSubtotal(detalle.getSubtotal());
        d.setLibro(detalle.getLibro());
        d.setFactura(detalle.getFactura());

        return detalleDao.save(d);
    }

    @Override
    public void delete(int id) {
        if (detalleDao.existsById(id)) {
            detalleDao.deleteById(id);
        }
    }
}
