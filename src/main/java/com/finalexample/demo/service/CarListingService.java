package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.CarListing;
import com.finalexample.demo.model.response.CarListingResponse;
import com.finalexample.demo.repository.CarListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarListingService {

    private final ImageService imageService;

    private final CarListingRepository carListingRepository;

    public List<CarListingResponse> retrieveAll() {
        List<CarListing> carListings = carListingRepository.findAll();
        return carListings
                .stream()
                .map(this::buildCarListingResponse)
                .collect(Collectors.toList());
    }

    private CarListingResponse buildCarListingResponse(CarListing carListing) {
        CarListingResponse carListingResponse = new CarListingResponse();

        carListingResponse.setId(carListing.getId());
        carListingResponse.setTitle(carListing.getTitle());
        carListingResponse.setDescription(carListing.getDescription());
        carListingResponse.setPrice(carListing.getPrice());
        // Creates /upload-image/<ID>/<IMAGE_NAME>
        String imagePath = imageService.getImagePath(carListing.getId(), carListing.getImageName());

        carListingResponse.setImagePath(imagePath);

        carListingResponse.setPower(carListing.getCar().getPower());
        carListingResponse.setFuelType(carListing.getCar().getFuelType());
        carListingResponse.setBrand(carListing.getCar().getBrand());
        carListingResponse.setModel(carListing.getCar().getModel());
        carListingResponse.setYear(carListing.getCar().getYear());
        return carListingResponse;
    }
}
