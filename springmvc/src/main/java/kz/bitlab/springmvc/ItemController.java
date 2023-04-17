package kz.bitlab.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @GetMapping(value = "/add-item")
    public String openADDItem() {
        return "add-item";
    }

    @PostMapping(value = "/add-item")
    public String addItemPost(@RequestParam(name = "item-name")String name,
                              @RequestParam(name = "item-price")int price,
                              @RequestParam(name = "item-amount")int amount) {
        String redirect = "/item/add-item?error";
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setAmount(amount);

        if (price >= 500000) {
            item.setLevel("expensive");
        } else if (price >= 300000) {
            item.setLevel("medium");
        } else {
            item.setLevel("cheap");
        }

        if(DBManager.addItem(item) == true) {
            redirect = "/item/add-item?success";
        }
        return "redirect:" + redirect;

    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        ArrayList<Item> items = DBManager.getAllItems();
        model.addAttribute("items", items);
        return "home";
    }

    @GetMapping(value = "/details")
    public String openDetails(@RequestParam(name = "id") Long id, Model model) {
        Item item = DBManager.getItem(id);
        model.addAttribute("item",item);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "item-name")String name,
                             @RequestParam(name = "item-price")int price,
                             @RequestParam(name = "item-amount")int amount,
                             @RequestParam(name = "item-id") Long id) {
        String redirect = "/item/update?error";
        Item updItem = new Item();
        updItem.setId(id);
        updItem.setName(name);
        updItem.setPrice(price);
        updItem.setAmount(amount);

        if (price >= 500000) {
            updItem.setLevel("expensive");
        } else if (price >= 300000) {
            updItem.setLevel("medium");
        } else {
            updItem.setLevel("cheap");
        }

        if (DBManager.updateItem(updItem) == true) {
            redirect = "/item/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "item-id") Long id) {
        String redirect = "/item/delete?error";

        if (DBManager.deleteItem(id)) {
            redirect = "/item/home";
        }

        return "redirect:" + redirect;
    }
}
