package com.finalexample.demo.controller;

import com.finalexample.demo.model.request.RegisterCarRequest;
import com.finalexample.demo.service.CarListingService;
import com.finalexample.demo.service.RegisterCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController extends BaseController{

    private final RegisterCarService registerCarService;

    private final CarListingService carListingService;

    @GetMapping("")
    public String getAdminPanelView(Model model) {
        model.addAttribute("carListings", carListingService.retrieveAll());
        return ok("admin-panel", model);
    }

    @GetMapping("/register-car")
    public String getRegisterCarView(Model model) {
        model.addAttribute("registerCarRequest", new RegisterCarRequest());
        return ok("register-car", model);
    }

    @PostMapping("/register-car")
    public String registerCar(RegisterCarRequest registerCarRequest, Model model) throws IOException {
        registerCarService.registerCar(registerCarRequest);
        return ok("success", model);
    }
}
