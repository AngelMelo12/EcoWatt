package com.fiap.gs.ecowatt.service;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import com.fiap.gs.ecowatt.model.dto.ElectronicDeviceNewDTO;
import com.fiap.gs.ecowatt.model.dto.ElectronicDeviceUpdateDTO;
import com.fiap.gs.ecowatt.repository.ElectronicDeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectronicDeviceService {

    private final ElectronicDeviceRepository electronicDeviceRepository;
    private final UserService userService;

    public List<ElectronicDevice> findAllByUserId(Long userId) {
        return electronicDeviceRepository.findByUser_Id(userId);
    }

    public void create(ElectronicDeviceNewDTO electronicDeviceRequest) {
        var authenticatedUser = userService.getAuthenticatedUser();
        electronicDeviceRepository.save(ElectronicDeviceNewDTO.toElectronicDevice(electronicDeviceRequest, authenticatedUser));
    }

    public ElectronicDeviceUpdateDTO getDtoFromId(Long id) {
        return electronicDeviceRepository.findById(id)
                .map(ElectronicDeviceUpdateDTO::fromElectronicDevice)
                .orElseThrow();
    }

    public void delete(Long id) {
        verificarSeExisteElectronicDevice(id);
        electronicDeviceRepository.deleteById(id);
    }

    public ElectronicDevice update(ElectronicDeviceUpdateDTO electronicDeviceRequest){
        var electronicDeviceToUpdate = verificarSeExisteElectronicDevice(electronicDeviceRequest.getId());
        return electronicDeviceRepository.save(ElectronicDeviceUpdateDTO.toElectronicDevice(electronicDeviceToUpdate, electronicDeviceRequest));
    }

    private ElectronicDevice verificarSeExisteElectronicDevice(Long id) {
        return electronicDeviceRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Dispositivo não encontrado")
                );
    }
}
