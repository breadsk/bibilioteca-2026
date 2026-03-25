package com.biblioteca.service;

import com.biblioteca.model.Libro;
import com.biblioteca.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired    
    private LibroRepository libroRepository;

    public List<Libro> getBooks(){
        return libroRepository.getAllBooks();
    }

    public Libro saveBook(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro getBookById(int id){
        return libroRepository.getBookById(id);
    }

    public Libro getBookByIsbn(String isbn){
        return libroRepository.getBookByIsbn(isbn);
    }

    public Libro updateBook(Libro libro){
        return libroRepository.updateBook(libro);
    }

    public String deleteBook(int id){
        libroRepository.deleteBook(id);
        return "Producto eliminado";
    }

    public int totalLibrosV1(){
        return libroRepository.getAllBooks().size();
    }

    public int totalLibrosV2(){
        return libroRepository.totalLibros();
    }

    public long countBooksByYear(int anio) {
        return libroRepository.getAllBooks().stream()
                .filter(libro -> libro.getFechaPublicacion() == anio)
                .count();
    }

    // Método para obtener el libro más antiguo (menor año de publicación)
    public Optional<Libro> getOldestBook() {
        return libroRepository.getAllBooks().stream()
                .min(Comparator.comparingInt(Libro::getFechaPublicacion));
    }

     // Método adicional: Retorna un mapa con la cantidad de libros por año
    public Map<Integer, Long> countBooksByYearGrouped() {
        return libroRepository.getAllBooks().stream()
                .collect(Collectors.groupingBy(
                    Libro::getFechaPublicacion,
                    Collectors.counting()
                ));
    }

    
}
