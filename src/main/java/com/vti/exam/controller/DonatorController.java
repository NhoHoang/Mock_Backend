package com.vti.exam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.exam.dto.DonatorPostDTO;
import com.vti.exam.entity.Donator;
import com.vti.exam.service.IDonatorService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/donator")
@Validated
public class DonatorController {

	@Autowired
	private IDonatorService service;

	@GetMapping()
	public ResponseEntity<?> getAllPost() {
		ArrayList<Donator> entities = service.getAllDonator();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping(value = "/check/{phone}")
	public Donator findDonatorByPhone(@PathVariable(name = "phone") String phone) {
		// get entity
		Donator donator = service.findDonatorByPhone(phone);
		// return result
		return donator;
	}

	@PostMapping()
	public Donator createDonator(@RequestBody Donator donator) {

		service.createDonator(donator);

		return donator;
	}

	@PostMapping(value = "/donatorpost")
	public ResponseEntity<?> createDonate(@RequestBody DonatorPostDTO dto) {
		Donator donator = service.findDonatorByPhone(dto.getPhone());
		if (donator == null) {
			Donator donatorNew = createDonator(dto.toDonatorEntity());
			service.createDonatePost(donatorNew.getId(), dto.getPost().getId(), dto.getMessage(), dto.getMoneyDonation());
		} else {
			service.createDonatePost(donator.getId(), dto.getPost().getId(), dto.getMessage(), dto.getMoneyDonation());
		}
		return new ResponseEntity<>("Đã quyên góp, xin cảm ơn!", HttpStatus.OK);
	}
}
