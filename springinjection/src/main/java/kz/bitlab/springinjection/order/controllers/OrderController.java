package kz.bitlab.springinjection.order.controllers;

import kz.bitlab.springinjection.order.entities.Order;
import kz.bitlab.springinjection.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/add-order")
    public String openAddOrder() {
        return "add-order";
    }

    @PostMapping(value = "/add-order")
    public String addOrder(@RequestParam(name = "user-name") String name,
                           @RequestParam(name = "food") String food,
                           @RequestParam(name = "food-amount") int amount,
                           @RequestParam(name = "user-address") String address) {
        String words[] = food.split(" ");

        Order order = new Order();
        order.setUserName(name);
        order.setFoodName(words[0]);
        order.setFoodPrice(Integer.parseInt(words[1]));
        order.setFoodAmount(amount);
        order.setUserAddress(address);
        order.setLinkToImage(words[2]);

        orderService.addOrder(order);

        return "redirect:/order/add-order";
    }

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        List<Order> orders = orderService.getAllOrder();
        model.addAttribute("orders",orders);
        return "home";
    }
}
