package com.fiap.gs.ecowatt.controller;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import com.fiap.gs.ecowatt.model.User;
import com.fiap.gs.ecowatt.repository.ElectronicDeviceRepository;
import com.fiap.gs.ecowatt.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElectronicDeviceRepository electronicDeviceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/login/{emailNum}")
    public String createFastUser(@PathVariable String emailNum){
        var user = new User();
        user.setUsername("Meu Nome " + emailNum);
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("email" + emailNum + "@email.com");
        user.setZipCode("12345");
        userRepository.save(user);

        var electronicDevice = new ElectronicDevice();
        electronicDevice.setUser(user);
        electronicDevice.setName("TV Sony");
        electronicDevice.setCategory("Televisão");
        electronicDevice.setModel("Sony 80\"");
        electronicDevice.setWatts(BigDecimal.valueOf(1500));
        electronicDevice.setUsageInHours(10);
        electronicDeviceRepository.save(electronicDevice);

        var electronicDevice2 = new ElectronicDevice();
        electronicDevice2.setUser(user);
        electronicDevice2.setName("Radio");
        electronicDevice2.setCategory("Som");
        electronicDevice2.setModel("LG XBOOM");
        electronicDevice2.setWatts(BigDecimal.valueOf(1200));
        electronicDevice2.setUsageInHours(2);
        electronicDeviceRepository.save(electronicDevice2);
        return "login";
    }

    @GetMapping("/cadastrar")
    public String signup(){
        return "cadastrar";
    }

    @PostMapping("/cadastrar")
    @Transactional
    public String createUsuario(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "cadastrar";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "login";
    }
}
