package com.mycompany.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.entity.Device;
import com.mycompany.entity.NotFoundException;
import com.mycompany.repository.DeviceRepository;

@Service
public class DeviceService {
	
	@Autowired
	private DeviceRepository repo;
	
	public List<Device> showDeviceList(){
		return (List<Device>) repo.findAll();
	}
	
	public void saveDevice(Device device) {
		repo.save(device);
	}
	
	public void deleteDevice(int id) throws NotFoundException {
		int count = repo.countById(id);
		// Tìm được ID
		if(count == 0) {
			throw new NotFoundException("Could not find any device with ID " +id);
		}
		repo.deleteById(id);
	}
	
	public Device getDevicebyId(int id) throws NotFoundException {
		Optional<Device> device = repo.findById(id);
		// Tìm được ID
		if(device.isPresent()) {
			return device.get();
		}
		throw new NotFoundException("Could not find any device with ID " + id);
		
	}
}
