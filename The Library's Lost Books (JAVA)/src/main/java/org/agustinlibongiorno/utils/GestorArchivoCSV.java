package org.agustinlibongiorno.utils;

import org.agustinlibongiorno.entities.Author;
import org.agustinlibongiorno.entities.Book;
import org.agustinlibongiorno.service.BookService;

import java.io.*;
import java.util.*;

public class GestorArchivoCSV {
    private static final String BOOK_PATH = "src/main/resources/biblioteca-datos.txt";
    private static final String LIST_BOOKBYAUTHOR_PATH = "src/main/resources/report/LIST_BookByAUTHOR.txt";
    private static final String LIST_BOOKWITHOUTYEAR_PATH = "src/main/resources/report/LIST_BOOKWITHOUTYEAR.txt";
    private static final String LIST_BOOK_PATH = "src/main/resources/report/LIST_BOOK.txt";

    public static void ImportarCSV() throws FileNotFoundException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(BOOK_PATH));
            String line = br.readLine();
            while ((line = br.readLine()) != null){
                BookService.createBook(line);
            }
            System.out.println("Libros importados con Exito");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportarBooksByAuthor (Map<Author, List<Book>> list) {
        try (FileWriter fw = new FileWriter(LIST_BOOKBYAUTHOR_PATH)){
            fw.append("---- Books by Author ----\n");

            list.forEach((A,B) -> {
                try {
                    fw.append("\n📖 " + A.getNombre()).append("\n");
                    B.forEach(book -> {
                        try {
                            fw.append("  - " + book.getTitle() + " (" + book.getYear() + ")").append("\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("Lista booksByAuthor creada con exito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportBooksWithoutYear (List<Book> list) {
        try (FileWriter fw = new FileWriter(LIST_BOOKWITHOUTYEAR_PATH)){
            fw.append("---- Books Without Year ----\n");

            list.forEach(book -> {
                try {
                    fw.append("\n📖"+ book.getTitle() + ", " + book.getAuthor().getNombre()+ "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("Lista BooksWithoutYear creada con exito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exportListBooks() {
        Set<Book> list = BookService.getListBooksCorregido();
        List<Book> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);

        try (FileWriter fw = new FileWriter(LIST_BOOK_PATH)) {
            fw.append("---- List Books ----\n");

            for (Book book : sortedList) {
                fw.append("\n📖" + book.getTitle() + ", " + book.getAuthor().getNombre() + ", " + book.getYear() + "\n");
            }
            System.out.println("Lista BooksWithoutYear creada con éxito");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
