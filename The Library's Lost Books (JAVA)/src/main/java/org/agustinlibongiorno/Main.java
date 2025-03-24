package org.agustinlibongiorno;

import org.agustinlibongiorno.service.BookService;
import org.agustinlibongiorno.utils.GestorArchivoCSV;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        GestorArchivoCSV.ImportarCSV();
        BookService.createBooksByAuthor();
        BookService.createBooksWithoutYear();
        GestorArchivoCSV.exportListBooks();
        System.out.println("Creado por Agustin Li Bongiorno");

    }
}