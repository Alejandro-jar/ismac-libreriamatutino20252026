package com.distribuida.Model;

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {

    private Libro libro;
    private Autor autor;

    @BeforeEach
    public void setup() {

        autor = new Autor(1, "Gabriel", "García Márquez", "Colombiana");

        libro = new Libro();
        libro.setIdLibro(1);
        libro.setTitulo("Cien años de soledad");
        libro.setPrecio(25.50);
        libro.setStock(15);

        libro.setAutor(autor);
    }

    @Test
    public void testLibroConstructor() {
        assertAll("Validar constructor Libro",
                () -> assertEquals(1, libro.getIdLibro()),
                () -> assertEquals("Cien años de soledad", libro.getTitulo()),
                () -> assertEquals(25.50, libro.getPrecio()),
                () -> assertEquals(15, libro.getStock())
        );
    }

    @Test
    public void testLibroSetters() {
        autor = new Autor(2, "Julio", "Cortázar", "Argentina");

        libro = new Libro();
        libro.setIdLibro(2);
        libro.setTitulo("Rayuela");
        libro.setPrecio(32.99);
        libro.setStock(20);
        libro.setAutor(autor);

        assertAll("Validar setters Libro",
                () -> assertEquals(2, libro.getIdLibro()),
                () -> assertEquals("Rayuela", libro.getTitulo()),
                () -> assertEquals(32.99, libro.getPrecio()),
                () -> assertEquals(20, libro.getStock()),
                () -> assertEquals("Cortázar", libro.getAutor().getApellido())
        );
    }

    @Test
    public void testLibroToString() {
        String str = libro.toString();
        assertAll("Validar toString Libro",
                () -> assertTrue(str.contains("Cien años de soledad")),
                () -> assertTrue(str.contains("Gabriel")),
                () -> assertTrue(str.contains("Colombiana"))
        );
    }
}
