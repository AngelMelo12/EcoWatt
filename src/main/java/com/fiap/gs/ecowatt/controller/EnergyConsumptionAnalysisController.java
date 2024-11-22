package com.fiap.gs.ecowatt.controller;

import com.fiap.gs.ecowatt.model.dto.ElectronicDeviceNewDTO;
import com.fiap.gs.ecowatt.model.dto.EnergyConsumptionAnalysisNewDTO;
import com.fiap.gs.ecowatt.service.ElectronicDeviceService;
import com.fiap.gs.ecowatt.service.EnergyConsumptionAnalysisService;
import com.fiap.gs.ecowatt.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analises")
public class EnergyConsumptionAnalysisController {

    @Autowired
    private EnergyConsumptionAnalysisService energyConsumptionAnalysisService;
    @Autowired
    private ElectronicDeviceService electronicDeviceService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getEnergyConsumptionAnalysis(Model model) {
        var authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("analysisList",
                energyConsumptionAnalysisService.findAllByUserId(authenticatedUser.getId()));
        return "analises";
    }

    @GetMapping("/create")
    public String getForm(Model model) {
        var authenticatedUser = userService.getAuthenticatedUser();
        model.addAttribute("electronicDevices", electronicDeviceService.findAllByUserId(authenticatedUser.getId()));
        model.addAttribute("energyConsumptionAnalysisNewDTO", new EnergyConsumptionAnalysisNewDTO());
        return "new-analise";
    }

    @PostMapping
    public String newEnergyConsumptionAnalysis(@ModelAttribute @Valid EnergyConsumptionAnalysisNewDTO energyConsumptionAnalysisNewDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-analise";
        }
        energyConsumptionAnalysisService.create(energyConsumptionAnalysisNewDTO);
        return "redirect:/analises";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        energyConsumptionAnalysisService.delete(id);
        return "redirect:/analises";
    }
}
