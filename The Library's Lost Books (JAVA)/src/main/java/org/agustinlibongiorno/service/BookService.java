package org.agustinlibongiorno.service;

import lombok.Getter;
import org.agustinlibongiorno.entities.Author;
import org.agustinlibongiorno.entities.Book;
import org.agustinlibongiorno.utils.GestorArchivoCSV;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class BookService {
    @Getter
    private static Set<Book> listBooksCorregido = new HashSet<>();

    public static void createBook(String line) {
        String[] v = line.split(",");

        String title = v[0].trim();
        Author author = null;

        if (v.length > 1) {
            String authorStr = v[1].trim();
            author = authorStr.isEmpty() ? AuthorService.getOCreate(null) : AuthorService.getOCreate(authorStr);
        }

        int year = 0;
        if (v.length > 2) {
            String yearStr = v[2].trim();
            if (!yearStr.isEmpty()) {
                year = checkYear(yearStr);
            }
        }

        for (Book existingBook : listBooksCorregido) {
            if (existingBook.getTitle().equals(title) && existingBook.getAuthor().equals(author)) {
                if (existingBook.getYear() == 0 && year > 0) {
                    existingBook.setYear(year);
                }
                return;
            }
        }

        Book book = new Book(title, author, year);
        listBooksCorregido.add(book);

    }


    //Metodos Auxiliares
    private static int checkYear(String str) {
        if (str == null || str.trim().isEmpty()) {
            return 0;
        }
        int yearActual = Year.now().getValue();
        try {
            int year = Integer.parseInt(str);
            if (year > 0 && year < yearActual){
                return year;
            }else {
                return 0;
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void readListBook(){
        listBooksCorregido.forEach(book -> System.out.println(book.toString()));
    }

    //Exportar Reportes
    public static void createBooksByAuthor() {
        Map<String, Author> listAuthor = AuthorService.getListAuthor();
        Map<Author, List<Book>> booksByAuthor = new HashMap<>();

        for (Author author : listAuthor.values()) {
            List<Book> listaFiltrada = listBooksCorregido.stream()
                    .filter(book -> book.getAuthor().equals(author))
                    .collect(Collectors.toList());
            booksByAuthor.put(author, listaFiltrada);
        }

        GestorArchivoCSV.exportarBooksByAuthor(booksByAuthor);

    }

    public static void createBooksWithoutYear() {
            List<Book> listaFiltrada = listBooksCorregido.stream()
                .filter(book -> book.getYear() == 0)
                .collect(Collectors.toList());
            GestorArchivoCSV.exportBooksWithoutYear(listaFiltrada);
        }


}
