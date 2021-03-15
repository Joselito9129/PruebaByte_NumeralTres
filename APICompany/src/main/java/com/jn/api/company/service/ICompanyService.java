package com.jn.api.company.service;

import com.jn.api.common.service.IGenericService;
import com.jn.api.company.model.CompanyEntity;

public interface ICompanyService extends IGenericService<CompanyEntity> {

	public boolean existsByCompanyName(String companyName);

}
