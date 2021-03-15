package com.jn.api.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jn.api.company.model.CompanyEntity;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Integer> {

	public abstract boolean existsByCompanyName(String companyName);

}
