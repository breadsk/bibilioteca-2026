package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;
        
    @GetMapping   
    public List<Libro> getBooks(){
        return libroService.getBooks();
    }

    @GetMapping("{id}")
    public Libro getBookById(@PathVariable int id){
        return libroService.getBookById(id);
    }

    @PostMapping
    public Libro saveBook(@RequestBody Libro libro){
        return libroService.saveBook(libro);
    }
    
    @PutMapping("{id}")
    public Libro updateBook(@PathVariable int id,@RequestBody Libro libro){
        return libroService.updateBook(libro);
    }

    @DeleteMapping("{id}")
    public String deleteBook(@PathVariable int id){
        return libroService.deleteBook(id);
    }

}
