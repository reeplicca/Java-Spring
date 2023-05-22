package kz.bitlab.springrestappdto.controllers;

import kz.bitlab.springrestappdto.entities.Brand;
import kz.bitlab.springrestappdto.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public Brand addBrand(@RequestBody Brand brand) {
        return brandService.addBrand(brand);
    }

    @GetMapping
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping(value = "/{id}")
    public Brand getBrand(@PathVariable(name = "id") Long id) {
        return brandService.getBrand(id);
    }

    @PutMapping
    public Brand updateBrand(@RequestBody Brand updBrand) {
        return brandService.updateBrand(updBrand);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBrand(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
    }

}
