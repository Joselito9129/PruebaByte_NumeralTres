package com.jn.api.company.convert;

import com.jn.api.company.model.CompanyCommon;
import com.jn.api.company.model.CompanyEntity;

public class CCompany {

	public static CompanyEntity modelToEntity(CompanyCommon model) {
		if (model != null) {
			CompanyEntity entity = new CompanyEntity();

			entity.setCompanyId(model.getId());
			entity.setCompanyAddress(model.getAddress());
			entity.setCompanyName(model.getName());
			entity.setCompanyNit(model.getNit());
			entity.setCompanyFounded(model.getFounded());
			entity.setCompanyStatus(model.getStatus());
			entity.setCompanyCreateBY(model.getCreateBY());
			entity.setCompanyCreateAT(model.getCreateAT());
			entity.setCompanyUpdatedBY(model.getUpdatedBY());
			entity.setCompanyUpdatedAT(model.getUpdatedAT());

			return entity;
		} else {
			return null;
		}

	}

	public static CompanyCommon entityToModel(CompanyEntity entity) {
		if (entity != null) {
			CompanyCommon model = new CompanyCommon();

			model.setId(entity.getCompanyId());
			model.setAddress(entity.getCompanyAddress());
			model.setName(entity.getCompanyName());
			model.setNit(entity.getCompanyNit());
			model.setFounded(entity.getCompanyFounded());
			model.setStatus(entity.getCompanyStatus());
			model.setCreateBY(entity.getCompanyCreateBY());
			model.setCreateAT(entity.getCompanyCreateAT());
			model.setUpdatedBY(entity.getCompanyUpdatedBY());
			model.setUpdatedAT(entity.getCompanyUpdatedAT());

			return model;
		} else {
			return null;
		}

	}
}
