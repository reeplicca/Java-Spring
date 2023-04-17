package kz.bitlab.springjpa.Controller;

import kz.bitlab.springjpa.DB.Item;
import kz.bitlab.springjpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value = "/add-item")
    public String openAddItem() {
        return "add-item";
    }

    @PostMapping(value = "/add-item")
    public String addItemPost(@RequestParam(name = "item-name") String name,
                              @RequestParam(name = "item-price") int price,
                              @RequestParam(name = "item-amount") int amount) {
        String redirect = "/item/add-item?error";

        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setAmount(amount);

        if (itemRepository.save(item) != null) {
            redirect = "/item/home";
        }

        return "redirect:" + redirect;

    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "home";
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable(name = "id") Long id, Model model) {
        Item item = itemRepository.findAllById(id);
        model.addAttribute("item",item);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "item-id") Long id,
                             @RequestParam(name = "item-name") String name,
                             @RequestParam(name = "item-price") int price,
                             @RequestParam(name = "item-amount") int amount) {
        String redirect = "/item/update?error";

        Item item = itemRepository.findAllById(id);
        item.setName(name);
        item.setPrice(price);
        item.setAmount(amount);

        if (itemRepository.save(item) != null) {
            redirect = "/item/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "item-id") Long id){
        itemRepository.deleteById(id);

        return "redirect:home";
    }
}
