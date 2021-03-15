package com.jn.api.company.input_output;

import java.util.List;

import com.jn.api.common.input_output.BaseOutput;
import com.jn.api.company.model.CompanyCommon;

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
public class CompanyResponse extends BaseOutput {

	private CompanyCommon company;
	private List<CompanyCommon> companies;
}
