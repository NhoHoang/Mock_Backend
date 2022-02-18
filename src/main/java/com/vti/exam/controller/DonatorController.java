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

import com.vti.exam.dto.DonatorDTO;
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
	public boolean existsDonatorByPhone(@PathVariable(name = "phone") String phone) {
		// get entity
		boolean result = service.existsDonatorByPhone(phone);

		// return result
		return result;
	}

	@PostMapping()
	public Donator createDonator(@RequestBody Donator donator) {

		service.createDonator(donator);

		return donator;
	}

	@PostMapping(value = "/donatorpost")
	public ResponseEntity<?> createDonate(@RequestBody DonatorPostDTO dto) {

		if (!existsDonatorByPhone(dto.getPhone())) {
			// create Donator
			Donator donator = createDonator(dto.toDonatorEntity());

			// ==> get donator.id
			// int donatorId = donator.getBody().getId();

			service.createDonatePost(donator, dto.toDonatorPostEntity());
		}

		return new ResponseEntity<>("Đang quyên góp", HttpStatus.OK);
	}
}
