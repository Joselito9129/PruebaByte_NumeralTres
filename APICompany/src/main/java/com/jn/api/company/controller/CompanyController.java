package com.jn.api.company.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jn.api.common.util.Response;
import com.jn.api.company.convert.CCompany;
import com.jn.api.company.input_output.CompanyRequest;
import com.jn.api.company.input_output.CompanyResponse;
import com.jn.api.company.model.CompanyCommon;
import com.jn.api.company.model.CompanyEntity;
import com.jn.api.company.service.ICompanyService;

@RestController
@RequestMapping("${url.private.company}")
public class CompanyController {

	private Logger log = LogManager.getLogger(this);

	@Value("${company.status.active}")
	private String statusActive;
	@Value("${company.status.inactive}")
	private String statusInactive;
	@Value("${company.status.pending}")
	private String statusPending;

	private ICompanyService companyService;

	@Autowired
	public CompanyController(ICompanyService companyService) {
		this.companyService = companyService;
	}

	@Async
	@PostMapping("/save")
	public ListenableFuture<ResponseEntity<CompanyResponse>> save(@RequestBody CompanyRequest request) {
		log.info("BEGIN METHOD COMPANY: save()");
		CompanyResponse response = new CompanyResponse();

		if (request.getCompany() == null) {
			response.basePopulate(Response.COMPANY_NOT_NULL);
		} else if (request.getUser() == null || request.getUser().isEmpty()) {
			response.basePopulate(Response.USER_NOT_EMPTY);
		} else if (request.getCompany().getName() == null || request.getCompany().getName().isEmpty()) {
			response.basePopulate(Response.NAME_NOT_EMPTY);
		} else if (request.getCompany().getNit() == null || request.getCompany().getNit().isEmpty()) {
			response.basePopulate(Response.NIT_NOT_EMPTY);
		} else if (request.getCompany().getAddress() == null || request.getCompany().getAddress().isEmpty()) {
			response.basePopulate(Response.ADDRESS_NOT_EMPTY);
		} else if (request.getCompany().getFounded() == null) {
			response.basePopulate(Response.FOUNDED_NOT_EMPTY);
		} else {

			try {
				boolean exist = this.companyService.existsByCompanyName(request.getCompany().getName());
				if (exist) {
					response.basePopulate(Response.COMPANY_EXISTS);
				} else {

					request.getCompany().setCreateBY(request.getUser());
					request.getCompany().setStatus(statusPending);

					response.setCompany(CCompany
							.entityToModel(this.companyService.save(CCompany.modelToEntity(request.getCompany()))));
					response.basePopulate(Response.SUCCESS_RESPONSE);

				}
			} catch (Exception e) {
				log.debug("ERROR METHOD COMPANY: save() --> ", e.getMessage());
				response.basePopulate(Response.ERROR_TRY);
			}

		}

		log.info("END METHOD COMPANY: save()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}

	@Async
	@PostMapping("/confirm")
	public ListenableFuture<ResponseEntity<CompanyResponse>> confirm(@RequestBody CompanyRequest request) {
		log.info("BEGIN METHOD COMPANY: confirm()");
		CompanyResponse response = new CompanyResponse();
		if (request.getCompany() == null) {
			response.basePopulate(Response.COMPANY_NOT_NULL);
		} else if (request.getUser() == null || request.getUser().isEmpty()) {
			response.basePopulate(Response.USER_NOT_EMPTY);
		} else if (request.getCompany().getId() == null) {
			response.basePopulate(Response.ID_NOT_EMPTY);
		} else {
			try {
				CompanyEntity company = this.companyService.findById(request.getCompany().getId());
				if (company != null) {
					company.setCompanyStatus(statusActive);
					company.setCompanyUpdatedBY(request.getUser());

					this.companyService.save(company);

					response.basePopulate(Response.SUCCESS_RESPONSE);
					response.setCompany(CCompany.entityToModel(company));

				} else {
					response.basePopulate(Response.COMPANY_NOT_EXISTS);
				}

			} catch (Exception e) {
				log.debug("ERROR METHOD COMPANY: confirm() --> ", e.getMessage());
				response.basePopulate(Response.ERROR_TRY);
			}
		}

		log.info("END METHOD COMPANY: confirm()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}

	@Async
	@PostMapping("/delete")
	public ListenableFuture<ResponseEntity<CompanyResponse>> delete(@RequestBody CompanyRequest request) {
		log.info("BEGIN METHOD COMPANY: delete()");
		CompanyResponse response = new CompanyResponse();
		if (request.getCompany() == null) {
			response.basePopulate(Response.COMPANY_NOT_NULL);
		} else if (request.getUser() == null || request.getUser().isEmpty()) {
			response.basePopulate(Response.USER_NOT_EMPTY);
		} else if (request.getCompany().getId() == null) {
			response.basePopulate(Response.ID_NOT_EMPTY);
		} else {
			try {
				CompanyEntity company = this.companyService.findById(request.getCompany().getId());
				if (company != null) {
					company.setCompanyStatus(statusInactive);
					company.setCompanyUpdatedBY(request.getUser());

					this.companyService.save(company);

					response.basePopulate(Response.SUCCESS_RESPONSE);

				} else {
					response.basePopulate(Response.COMPANY_NOT_EXISTS);
				}

			} catch (Exception e) {
				log.debug("ERROR METHOD COMPANY: delete() --> ", e.getMessage());
				response.basePopulate(Response.ERROR_TRY);
			}
		}

		log.info("END METHOD COMPANY: delete()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}

	@Async
	@PostMapping("/update")
	public ListenableFuture<ResponseEntity<CompanyResponse>> update(@RequestBody CompanyRequest request) {
		log.info("BEGIN METHOD COMPANY: update()");
		CompanyResponse response = new CompanyResponse();
		if (request.getCompany() == null) {
			response.basePopulate(Response.COMPANY_NOT_NULL);
		} else if (request.getUser() == null || request.getUser().isEmpty()) {
			response.basePopulate(Response.USER_NOT_EMPTY);
		} else if (request.getCompany().getId() == null) {
			response.basePopulate(Response.ID_NOT_EMPTY);
		} else {
			try {
				CompanyEntity company = this.companyService.findById(request.getCompany().getId());
				if (company != null) {

					if (company.getCompanyStatus().equals(statusInactive)
							|| company.getCompanyStatus().equals(statusPending)) {
						response.basePopulate(Response.STATUS_INCOMPATIBLE);

					} else {
						company.setCompanyName(
								(request.getCompany().getName() != null || request.getCompany().getName().isEmpty())
										? request.getCompany().getName()
										: company.getCompanyName());

						company.setCompanyNit(
								(request.getCompany().getNit() != null || request.getCompany().getNit().isEmpty())
										? request.getCompany().getNit()
										: company.getCompanyNit());

						company.setCompanyAddress((request.getCompany().getAddress() != null
								|| request.getCompany().getAddress().isEmpty()) ? request.getCompany().getAddress()
										: company.getCompanyAddress());

						company.setCompanyFounded(
								(request.getCompany().getFounded() != null) ? request.getCompany().getFounded()
										: company.getCompanyFounded());

						company.setCompanyUpdatedBY(request.getUser());

						this.companyService.save(company);

						response.basePopulate(Response.SUCCESS_RESPONSE);
						response.setCompany(CCompany.entityToModel(company));
					}

				} else {
					response.basePopulate(Response.COMPANY_NOT_EXISTS);
				}

			} catch (Exception e) {
				log.debug("ERROR METHOD COMPANY: update() --> ", e.getMessage());
				response.basePopulate(Response.ERROR_TRY);
			}
		}

		log.info("END METHOD COMPANY: update()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}

	@Async
	@PostMapping("/retriveById")
	public ListenableFuture<ResponseEntity<CompanyResponse>> getById(@RequestBody CompanyRequest request) {
		log.info("BEGIN METHOD COMPANY: getById()");
		CompanyResponse response = new CompanyResponse();
		if (request.getCompany() == null) {
			response.basePopulate(Response.COMPANY_NOT_NULL);
		} else if (request.getCompany().getId() == null) {
			response.basePopulate(Response.ID_NOT_EMPTY);
		} else {
			try {
				CompanyEntity company = this.companyService.findById(request.getCompany().getId());
				if (company != null) {
					if (company.getCompanyStatus().equals(statusInactive)) {
						response.basePopulate(Response.STATUS_INCOMPATIBLE);

					} else {
						response.basePopulate(Response.SUCCESS_RESPONSE);
						response.setCompany(CCompany.entityToModel(company));
					}
				} else {
					response.basePopulate(Response.COMPANY_NOT_EXISTS);
				}

			} catch (Exception e) {
				log.debug("ERROR METHOD COMPANY: getById() --> ", e.getMessage());
				response.basePopulate(Response.ERROR_TRY);
			}
		}

		log.info("END METHOD COMPANY: getById()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}

	@Async
	@PostMapping("/retriveAll")
	public ListenableFuture<ResponseEntity<CompanyResponse>> getAll() {
		log.info("BEGIN METHOD COMPANY: getAll()");
		CompanyResponse response = new CompanyResponse();

		try {
			List<CompanyCommon> companies = this.companyService.findAll().stream().map(company -> {
				return CCompany.entityToModel(company);
			}).collect(Collectors.toList());
			if (companies.isEmpty()) {
				response.basePopulate(Response.COMPANY_NOT_EXISTS);
			} else {
				response.basePopulate(Response.SUCCESS_RESPONSE);
				response.setCompanies(companies);
			}

		} catch (Exception e) {
			log.debug("ERROR METHOD COMPANY: getAll() --> ", e.getMessage());
			response.basePopulate(Response.ERROR_TRY);

		}

		log.info("END METHOD COMPANY: getAll()");
		return new AsyncResult<>(ResponseEntity.ok(response));
	}
}
