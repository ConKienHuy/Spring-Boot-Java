package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.entity.Device;
import com.mycompany.entity.NotFoundException;
import com.mycompany.service.DeviceService;

@Controller
public class DeviceController {
	
	@Autowired
	private DeviceService service;
	
	// Show device list Hiển thị ra view
	@RequestMapping("/devices")
	public String showDeviceList(Model model) {
		List<Device> devices = service.showDeviceList();
		model.addAttribute("deviceList" , devices);
		
		return "device_template/devices";
	}
	
	@GetMapping("devices/new")
	public String showForm(Model model) {
		model.addAttribute("device", new Device());
		model.addAttribute("pageTitle", "Thêm thiết bị mới");
		
		return "device_template/device_form";
	}
	
	@PostMapping("devices/save")
	public String saveDevice(Device device, RedirectAttributes ra) {
		service.saveDevice(device);
		ra.addFlashAttribute("message", "Save successfully");
        return "redirect:/devices";
	}
	
	
	@GetMapping("/devices/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model, RedirectAttributes ra) {
        try {
            Device device = service.getDevicebyId(id);
            model.addAttribute("device", device);
            model.addAttribute("pageTitle", "Edit Device (ID: " + id + ")");

            return "/device_template/device_form";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/divices";
        }
    }

    @GetMapping("/devices/delete/{id}")
    public String deleteDevice(@PathVariable("id") int id, RedirectAttributes ra ) {
    	try {
    		service.deleteDevice(id);
    		ra.addFlashAttribute("message", "Device deleted successfully!");
    		
    	}catch(NotFoundException ex){
    		// Nếu không tìm thấy device
    		ra.addFlashAttribute("message", ex.getMessage());
    	}
    	// Trở về trang devices chính
    	return "redirect:/devices";
    }
}
