package kz.bitlab.springtest1.Controller;

import kz.bitlab.springtest1.CarRepository;
import kz.bitlab.springtest1.DB.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/car")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping(value = "/add-car")
    public String addCarOpen() {
        return "add-car";
    }

    @PostMapping(value = "/add-car")
    public String addCarPost(@RequestParam(name = "car-name") String name,
                             @RequestParam(name = "car-engine") String engine,
                             @RequestParam(name = "car-volume") int volume) {
        String redirect = "/car/add-car?error";

        Car car = new Car();
        car.setName(name);
        car.setEngine(engine);
        car.setVolume(volume);

        if (carRepository.save(car) != null) {
            redirect = "/car/home";
        }

        return "redirect:" + redirect;
    }

    @GetMapping(value = "/home")
    public String homeOpen(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars",cars);
        return "home";
    }

    @GetMapping(value = "/details/{id_details}")
    public String detailsOpen(@PathVariable(name = "id_details") Long id, Model model) {
        Car car = carRepository.findAllById(id);
        model.addAttribute("car",car);
        return "details";
    }

    @PostMapping(value = "/update")
    public String updatePost(@RequestParam(name = "car-id") Long id,
                             @RequestParam(name = "car-name") String name,
                             @RequestParam(name = "car-engine") String engine,
                             @RequestParam(name = "car-volume") int volume){
        String redirect = "/car/update?error";

        Car car = carRepository.findAllById(id);
        car.setName(name);
        car.setEngine(engine);
        car.setVolume(volume);

        if (carRepository.save(car) != null) {
            redirect = "/car/home";
        }

        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete")
    public String deletePost(@RequestParam(name = "car-id") Long id) {
        String redirect = "/car/home";

        carRepository.deleteById(id);

        return "redirect:" + redirect;
    }
}
