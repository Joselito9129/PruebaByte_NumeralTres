package com.jn.api.company.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jn.api.company.model.CompanyEntity;
import com.jn.api.company.repository.CompanyRepo;
import com.jn.api.company.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService {

	private CompanyRepo companyRepo;

	@Autowired
	public CompanyServiceImpl(CompanyRepo companyRepo) {
		this.companyRepo = companyRepo;
	}

	@Override
	public List<CompanyEntity> findAll() {
		return this.companyRepo.findAll();
	}

	@Override
	public CompanyEntity save(CompanyEntity entity) {
		if (entity.getCompanyId() == null) {
			entity.setCompanyCreateAT(new Date());
		} else {
			entity.setCompanyUpdatedAT(new Date());
		}
		return this.companyRepo.save(entity);
	}

	@Override
	public CompanyEntity findById(Integer id) {
		return this.companyRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(CompanyEntity entity) {
		this.companyRepo.delete(entity);
	}

	@Override
	public void deleteById(Integer id) {
		this.companyRepo.deleteById(id);
	}

	@Override
	public long count() {
		return this.companyRepo.count();
	}

	@Override
	public boolean existsById(Integer id) {
		return this.companyRepo.existsById(id);
	}

	@Override
	public boolean existsByCompanyName(String companyName) {
		return this.companyRepo.existsByCompanyName(companyName);
	}

}
