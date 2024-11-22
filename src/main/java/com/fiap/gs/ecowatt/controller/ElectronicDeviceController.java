package com.fiap.gs.ecowatt.controller;

import com.fiap.gs.ecowatt.model.dto.ElectronicDeviceNewDTO;
import com.fiap.gs.ecowatt.model.dto.ElectronicDeviceUpdateDTO;
import com.fiap.gs.ecowatt.service.ElectronicDeviceService;
import com.fiap.gs.ecowatt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dispositivos")
public class ElectronicDeviceController {

    @Autowired
    private ElectronicDeviceService electronicDeviceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getElectronicDevices(Model model) {
        var authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("electronicDevices", electronicDeviceService.findAllByUserId(authenticatedUser.getId()));
        return "dispositivos";
    }

    @GetMapping("/create")
    public String getForm(Model model) {
        model.addAttribute("electronicDeviceNewDTO", new ElectronicDeviceNewDTO());
        return "new-dispositivo";
    }

    @PostMapping
    public String newElectronicDevice(@ModelAttribute @Valid ElectronicDeviceNewDTO electronicDeviceNewDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-dispositivo";
        }
        electronicDeviceService.create(electronicDeviceNewDTO);
        return "redirect:/dispositivos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        electronicDeviceService.delete(id);
        return "redirect:/dispositivos";
    }

    @GetMapping("/edit/{id}")
    public String getFormEdicao(Model model, @PathVariable Long id){
        var electronicDeviceUpdateDTO = electronicDeviceService.getDtoFromId(id);
        model.addAttribute("electronicDeviceUpdateDTO", electronicDeviceUpdateDTO);
        return "update-dispositivo";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute @Valid ElectronicDeviceUpdateDTO electronicDeviceUpdateDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "update-dispositivo";
        }
        electronicDeviceService.update(electronicDeviceUpdateDTO);
        return "redirect:/dispositivos";
    }
}
