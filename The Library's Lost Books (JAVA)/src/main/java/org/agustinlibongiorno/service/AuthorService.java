package org.agustinlibongiorno.service;

import lombok.Getter;
import org.agustinlibongiorno.entities.Author;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class AuthorService {
    @Getter
    private static Map<String, Author> listAuthor = new TreeMap<>();

    public static Author getOCreate(String e) {
        if (e == null || e.trim().isEmpty()) {
            e = "Author Unknown";
        }
        Author newAuthor = new Author(e);
        listAuthor.putIfAbsent(e, newAuthor);
        return newAuthor;
    }
}
