package org.agustinlibongiorno.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Author implements Comparable<Author> {
    private String nombre;


    @Override
    public int compareTo(Author other) {
        return this.nombre.compareToIgnoreCase(other.nombre);
    }

    @Override
    public String toString() {
        return "Author(nombre=" + nombre + ")";
    }
}
