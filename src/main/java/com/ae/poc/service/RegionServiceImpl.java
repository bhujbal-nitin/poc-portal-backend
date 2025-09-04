package com.ae.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ae.poc.repo.RegionRepo;

@Service
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	private RegionRepo regionRepo;

	@Override
	public List<String> getAllRegion() {
		// TODO Auto-generated method stub
		List<String> regions = this.regionRepo.getAllRegion();
		return regions;
	}

}
