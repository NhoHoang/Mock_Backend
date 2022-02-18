package com.vti.exam.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vti.exam.dto.DonatorDTO;
import com.vti.exam.entity.Donator;
import com.vti.exam.entity.Donator_Post;
import com.vti.exam.repository.IDonatorPostRepository;
import com.vti.exam.repository.IDonatorRepository;

@Service
public class DonatorService implements IDonatorService {

	@Autowired
	private IDonatorRepository repository;

	@Autowired
	private IDonatorPostRepository Dprepository;

	@Override
	public ArrayList<Donator> getAllDonator() {

		return (ArrayList<Donator>) repository.findAll();
	}

	@Override
	public boolean existsDonatorByPhone(String phone) {
		return repository.existsByPhone(phone);
	}

	@Override
	public void createDonator(Donator donator) {
		repository.save(donator);
	}

	@Override
	public void createDonatePost(Donator donator, Donator_Post donatorPostEntity) {
		Donator_Post donator_Post = new Donator_Post(donator, donatorPostEntity.getPost(),
				donatorPostEntity.getTotal_money(), donatorPostEntity.getMessage());

		Dprepository.save(donator_Post);

	}

}
