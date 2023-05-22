package kz.bitlab.springrestappdto.controllers;

import kz.bitlab.springrestappdto.entities.Item;
import kz.bitlab.springrestappdto.entities.ItemDto;
import kz.bitlab.springrestappdto.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item")
public class HomeController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/home")
    public String openHome() {
        return "home";
    }

    @GetMapping(value = "/add-item")
    public String openAddItem() {
        return "add-item";
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable(name = "id") Long id, Model model) {
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item", itemDto);
        return "details";
    }
}
