package com.distribuida.service;

import com.distribuida.dao.FacturaDao;
import com.distribuida.model.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacturaService {

    public List<Factura> findAll();

    public Factura findOne(int id);
    public Factura save(Factura factura);
    public Factura update(int id, Factura factura);
    public  void delete(int id);
}
