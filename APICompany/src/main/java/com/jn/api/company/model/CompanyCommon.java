package com.jn.api.company.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompanyCommon {

	private Integer id;
	private String name;
	private String nit;
	private String address;
	private Date founded;
	private String status;
	private String createBY;
	private Date createAT;
	private String updatedBY;
	private Date updatedAT;

}
