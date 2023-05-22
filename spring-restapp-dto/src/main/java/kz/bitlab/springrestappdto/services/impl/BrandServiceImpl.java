package kz.bitlab.springrestappdto.services.impl;

import kz.bitlab.springrestappdto.entities.Brand;
import kz.bitlab.springrestappdto.repositories.BrandRepository;
import kz.bitlab.springrestappdto.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long id) {
        return brandRepository.findAllById(id);
    }

    @Override
    public Brand updateBrand(Brand updBrand) {
        Brand brand = brandRepository.findAllById(updBrand.getId());
        brand.setName(updBrand.getName());

        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
