package com.mycompany.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.entity.Device;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Integer> {
	public int countById(int id);
}
