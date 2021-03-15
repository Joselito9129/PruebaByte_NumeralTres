package com.jn.api.company.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer companyId;

	@Column(name = "name")
	private String companyName;

	@Column(name = "nit")
	private String companyNit;

	@Column(name = "address")
	private String companyAddress;

	@Column(name = "founded_at")
	private Date companyFounded;

	@Column(name = "status")
	private String companyStatus;

	@Column(name = "create_by")
	private String companyCreateBY;

	@Column(name = "create_at")
	private Date companyCreateAT;

	@Column(name = "updated_by")
	private String companyUpdatedBY;

	@Column(name = "updated_at")
	private Date companyUpdatedAT;

}
