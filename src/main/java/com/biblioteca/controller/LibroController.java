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
