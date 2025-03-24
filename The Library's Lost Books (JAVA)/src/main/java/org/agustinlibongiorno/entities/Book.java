package org.agustinlibongiorno.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Book implements Comparable<Book>{
    private String title;
    private Author author;
    private int year;

    @Override
    public int compareTo(Book other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "Book(title=" + title + ", author=" + author.getNombre() + ", year=" + year + ")";
    }

}
