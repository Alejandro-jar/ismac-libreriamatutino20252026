package com.distribuida.controller;

import com.distribuida.model.Factura;
import com.distribuida.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apifacturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // GET: listar todas las facturas
    @GetMapping
    public ResponseEntity<List<Factura>> findAll() {
        return ResponseEntity.ok(facturaService.findAll());
    }

    // GET: buscar factura por id
    @GetMapping("/{id}")
    public ResponseEntity<Factura> findOne(@PathVariable int id) {
        Optional<Factura> factura = facturaService.findOne(id);

        return factura.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: crear factura
    @PostMapping
    public ResponseEntity<Factura> save(@RequestBody Factura factura) {
        return ResponseEntity.ok(facturaService.save(factura));
    }

    // PUT: actualizar factura
    @PutMapping("/{id}")
    public ResponseEntity<Factura> update(@PathVariable int id,
                                          @RequestBody Factura factura) {

        Factura facturaActualizada = facturaService.update(id, factura);

        if (facturaActualizada == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(facturaActualizada);
    }

    // DELETE: eliminar factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        facturaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
