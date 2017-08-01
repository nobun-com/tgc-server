package com.go2.classes;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.go2.classes.business.service.AdminService;
import com.go2.classes.models.Admin;

@SpringBootApplication
public class GotoclassesApplication {

    AdminService adminService;

    @Autowired
    public GotoclassesApplication(AdminService adminService) {
	this.adminService = adminService;
	loadData();
    }

    private void loadData() {
	Admin admin = adminService.findByEmail("admin@tgc.com");
	if (Objects.isNull(admin)) {
	    admin = new Admin();
	    admin.setEmail("admin@tgc.com");
	    admin.setPassword("admin123#");
	    admin.setCreatedTime(new Date());
	    adminService.create(admin);
	}
    }

    public static void main(String[] args) {
	SpringApplication.run(GotoclassesApplication.class, args);
    }
}
