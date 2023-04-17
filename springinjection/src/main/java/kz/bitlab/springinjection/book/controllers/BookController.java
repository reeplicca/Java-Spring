package kz.bitlab.springinjection.book.controllers;

import kz.bitlab.springinjection.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    @Qualifier("detectiveBook")
    private Book book;

    @GetMapping(value = "/add-book")
    public String openBook(Model model){
        model.addAttribute("book", book);
        return "add-book";
    }

    @PostMapping(value = "/add-book")
    public String addBookPost(@RequestParam(name = "book-name") String name,
                              @RequestParam(name = "book-price") int price,
                              @RequestParam(name = "book-amount") int amount){
        book.setName(name);
        book.setPrice(price);
        book.setAmount(amount);

        return "redirect:add-book";
    }
}
