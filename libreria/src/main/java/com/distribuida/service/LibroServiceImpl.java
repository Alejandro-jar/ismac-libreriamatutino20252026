package com.distribuida.service;

import com.distribuida.dao.LibroDao;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    public List<Libro> findAll() {
        return libroDao.findAll();
    }

    @Override
    public Optional<Libro> findOne(int id) {
        return libroDao.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return libroDao.save(libro);
    }

    @Override
    public Libro update(int id, Libro libro) {

        Optional<Libro> libroExistente = libroDao.findById(id);

        if (!libroExistente.isPresent()) {
            return null;
        }

        Libro l = libroExistente.get();

        l.setTitulo(libro.getTitulo());
        l.setPrecio(libro.getPrecio());
        l.setStock(libro.getStock());
        l.setAutor(libro.getAutor());

        return libroDao.save(l);
    }

    @Override
    public void delete(int id) {
        if (libroDao.existsById(id)) {
            libroDao.deleteById(id);
        }
    }
}
