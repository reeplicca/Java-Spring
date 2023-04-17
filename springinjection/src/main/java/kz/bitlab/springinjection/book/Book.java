package kz.bitlab.springinjection.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Book {
    private Long id;
    private String name;
    private int price;
    private int amount;

    public abstract int calculateTotal();
}
