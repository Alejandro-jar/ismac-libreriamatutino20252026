package com.distribuida.Model;

import com.distribuida.model.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutorTest {

    private Autor autor;

    @BeforeEach
    public void setup() {
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Gabriel");
        autor.setApellido("García");
        autor.setNacionalidad("Colombiana");
    }

    @Test
    public void testAutorConstructor() {
        assertAll("Validar constructor Autor",
                () -> assertEquals(1, autor.getIdAutor()),
                () -> assertEquals("Gabriel", autor.getNombre()),
                () -> assertEquals("García Márquez", autor.getApellido()),
                () -> assertEquals("Colombiana", autor.getNacionalidad())
        );
    }

    @Test
    public void testAutorSetters() {
        autor = new Autor();
        autor.setIdAutor(2);
        autor.setNombre("Julio");
        autor.setApellido("Cortázar");
        autor.setNacionalidad("Argentina");

        assertAll("Validar setters Autor",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Julio", autor.getNombre()),
                () -> assertEquals("Cortázar", autor.getApellido()),
                () -> assertEquals("Argentina", autor.getNacionalidad())
        );
    }

    @Test
    public void testAutorToString() {
        String str = autor.toString();
        assertAll("Validar toString Autor",
                () -> assertTrue(str.contains("Gabriel")),
                () -> assertTrue(str.contains("García Márquez")),
                () -> assertTrue(str.contains("Colombiana"))
        );
    }
}
