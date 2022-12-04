package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.Car;
import com.finalexample.demo.model.entity.CarListing;
import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RegisterCarService {

    private final CarListingRepository carListingRepository;

    private final ImageService imageService;
    public void registerCar(RegisterCarRequest registerCarRequest) throws IOException {
        // TODO
        CarListing carListing = new CarListing();

        carListing.setTitle(registerCarRequest.getTitle());
        carListing.setDescription(registerCarRequest.getDescription());
        carListing.setPrice(registerCarRequest.getPrice());

        // Create Car Object
        Car car = new Car();
        car.setBrand(registerCarRequest.getBrand());
        car.setFuelType(registerCarRequest.getFuelType());
        car.setModel(registerCarRequest.getModel());
        car.setYear(registerCarRequest.getYear());
        car.setPower(registerCarRequest.getPower());

        carListing.setCar(car);

        // Save Car Listing without IMAGE
        CarListing savedCarListing =  carListingRepository.save(carListing);
        // Get Carr Listing's ID
        Long id = savedCarListing.getId();

        // Save IMAGE into a folder containing LISTING's ID
        String imagePath = imageService.saveImage(registerCarRequest.getImage(),id);

        // Set image name to car listing
        savedCarListing.setImageName(imagePath);

        // Update Car Listing
        carListingRepository.save(savedCarListing);
    }
}
