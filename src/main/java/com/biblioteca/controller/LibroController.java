package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
        List<Libro> libros = libroService.getBooks();

        Map<String, Object> response = new HashMap<>();
        if (libros == null || libros.isEmpty()) {

            response.put("timestamp", LocalDateTime.now());
            response.put("status", HttpStatus.OK.value());
            response.put("message", "No hay libros registrados");
            response.put("data", null); // Es mejor enviar lista vacía que null

            return ResponseEntity.ok(response);
        }
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("data", libros); // Es mejor enviar lista vacía que null
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public Libro getBookById(@PathVariable int id) {
        return libroService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public Libro getBookByIsbn(@PathVariable String isbn){
        return libroService.getBookByIsbn(isbn);
    }
    

    @GetMapping("/total")
    public ResponseEntity<?> totalLibrosV2(){
        int cantLibros = libroService.totalLibrosV1();

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("cantidad", cantLibros); // Es mejor enviar lista vacía que null
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/anio/{anio}/cantidad")
    public ResponseEntity<?> getBookCountByYear(@PathVariable int anio) {
        long cantidad = libroService.countBooksByYear(anio);
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("anio", anio);
        response.put("cantidad", cantidad);
        
        return ResponseEntity.ok(response);
    }

    //Optional es una clase introducida en Java 8 que actúa como un contenedor 
    //que puede tener un valor o estar vacío (null). 
    //Es una forma más segura de manejar valores que pueden ser nulos,
    //evitando el temido NullPointerException.
    @GetMapping("/antiguo")
    public ResponseEntity<?> getOldestBook() {
        Optional<Libro> libroAntiguo = libroService.getOldestBook();
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        
        if (libroAntiguo.isPresent()) {
            response.put("message", "Libro más antiguo encontrado");
            response.put("data", libroAntiguo.get());
            response.put("anio_publicacion", libroAntiguo.get().getFechaPublicacion());
        } else {
            response.put("message", "No hay libros registrados");
            response.put("data", null);
        }
        
        return ResponseEntity.ok(response);
    }

    // Endpoint para obtener estadísticas de libros por año
    @GetMapping("/estadisticas/anios")
    public ResponseEntity<?> getBooksStatisticsByYear() {
        Map<Integer, Long> estadisticas = libroService.countBooksByYearGrouped();
        
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.OK.value());
        response.put("estadisticas", estadisticas);
        
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public Libro saveBook(@RequestBody Libro libro) {
        return libroService.saveBook(libro);
    }

    @PutMapping("{id}")
    public Libro updateBook(@PathVariable int id, @RequestBody Libro libro) {
        return libroService.updateBook(libro);
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable int id) {
        return libroService.deleteBook(id);
    }


}
