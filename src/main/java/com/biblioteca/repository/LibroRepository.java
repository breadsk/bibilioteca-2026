package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LibroRepository {

    //Arreglo que va a guardar mis libros
    private List<Libro> listaLibros = new ArrayList<>();

    //metodo para inicializar los datos ficticios
    @PostConstruct
    // public void init(){
    //     listaLibros.add(new Libro(1,"978-0134685991","Effective Java","Addison-wesley",2018,"Joshua Bloch"));
    //     listaLibros.add(new Libro(2,"978-1617294956","Spring in action","Manning",2020,"Craig Walls"));
    //     listaLibros.add(new Libro(3,"978-1491950357","Design Data-Intensive Application","O'Reilly",2017,"Martin Kleppmann"));
    //     listaLibros.add(new Libro(4,"978-0132350884","Clean Code","Prentice Hall",2008,"Robert C.Martin"));
    // }

    //Metodo que retorna todos los libros
    public List<Libro> getAllBooks(){
        return listaLibros;
    }

    //Metodo que retorna un libro por su ID
    public Libro getBookById(int id){
        for(Libro libro : listaLibros){
            if(libro.getId() == id){
                return libro;
            }
        }
        return null;
    }

    //Metodo que returna un libro por su ISBN
    public Libro getBookByIsbn(String isbn){
        for(Libro libro : listaLibros){
            if(libro.getIsbn().equals(isbn)){
                return libro;
            }
        }
        return null;
    }

    //Metodo para guardar un libro
    public Libro save(Libro libro){
        listaLibros.add(libro);
        return libro;
    }

    //Metodo para actualizar un libro
    public Libro updateBook(Libro lib){
        int id = 0;
        int idPosicion = 0;

        //Primero vamos a buscar el libro
        for(int i = 0;i<listaLibros.size();i++){
            if(listaLibros.get(i).getId() == lib.getId()){
                id = lib.getId();
                idPosicion = i;
            }
        }

        Libro libro1 = new Libro();
        libro1.setId(id);
        libro1.setTitulo(lib.getTitulo());
        libro1.setAutor(lib.getAutor());
        libro1.setFechaPublicacion(lib.getFechaPublicacion());
        libro1.setEditorial(lib.getEditorial());
        libro1.setIsbn(lib.getIsbn());

        listaLibros.set(idPosicion,libro1);
        return libro1;
    }

    //Metodo para eliminar un libro
    public void deleteBook(int id){
        //Alternativa 1
        Libro libro = getBookById(id);
        if(libro != null){
            listaLibros.remove(libro);
        }

        //Alternativa 2
        int idPosicion = 0;
        for(int i = 0;i<listaLibros.size();i++){
            if(listaLibros.get(i).getId() == id){
                idPosicion = i;
                break;
            }
        }
        if(idPosicion > 0){
            listaLibros.remove(idPosicion);
        }

        //Alternativa 3
        listaLibros.removeIf(x -> x.getId() == id );
    }
}
