package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.CarListing;
import com.finalexample.demo.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarListingService {

    private final CarListingRepository carListingRepository;

    public List<CarListing> retrieveAll() {
        return carListingRepository.findAll();
    }
}
