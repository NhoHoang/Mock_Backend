package com.vti.exam.service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import com.vti.exam.dto.DonatorDTO;
import com.vti.exam.entity.Donator;
import com.vti.exam.entity.Donator_Post;

public interface IDonatorService {

	ArrayList<Donator> getAllDonator();

	boolean existsDonatorByPhone(String phone);

	void createDonator(Donator donator);

	void createDonatePost(Donator donator, Donator_Post donatorPostEntity);

}
