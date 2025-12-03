package com.distribuida.service;

import com.distribuida.dao.AutorDao;
import com.distribuida.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorDao autorDao;

    @Override
    public List<Autor> findAll() {
        return autorDao.findAll();
    }

    @Override
    public Optional<Autor> findOne(int id) {
        return autorDao.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return autorDao.save(autor);
    }

    @Override
    public Autor update(int id, Autor autor) {

        Optional<Autor> autorExistente = autorDao.findById(id);

        if (!autorExistente.isPresent()) {
            return null;
        }

        Autor a = autorExistente.get();

        a.setNombre(autor.getNombre());
        a.setApellido(autor.getApellido());
        a.setNacionalidad(autor.getNacionalidad());

        return autorDao.save(a);
    }

    @Override
    public void delete(int id) {
        if (autorDao.existsById(id)) {
            autorDao.deleteById(id);
        }
    }
}
