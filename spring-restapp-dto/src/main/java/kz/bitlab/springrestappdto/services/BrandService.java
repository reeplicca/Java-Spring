package kz.bitlab.springrestappdto.services;

import kz.bitlab.springrestappdto.entities.Brand;

import java.util.List;

public interface BrandService {
    Brand addBrand(Brand brand);
    List<Brand> getAllBrands();
    Brand getBrand(Long id);
    Brand updateBrand(Brand updBrand);
    void deleteBrand(Long id);
}
