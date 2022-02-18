package com.vti.exam.service;

import java.util.ArrayList;

import com.vti.exam.entity.Donator;

public interface IDonatorService {

	ArrayList<Donator> getAllDonator();

	void createDonator(Donator donator);

	void createDonatePost(int donatorId, int postId, String mess, int money);

	Donator findDonatorByPhone(String phone);

}
