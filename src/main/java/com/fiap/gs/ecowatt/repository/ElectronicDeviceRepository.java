package com.fiap.gs.ecowatt.repository;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectronicDeviceRepository extends JpaRepository<ElectronicDevice, Long> {
    List<ElectronicDevice> findByUser_Id(Long id);
}
