package com.jn.api.common.input_output;

import com.jn.api.common.util.Response;

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
public class BaseOutput {

	private String indicator;
	private String code;
	private String description;

	public void basePopulate(Response response) {
		this.code = response.getCode();
		this.indicator = response.getIndicator();
		this.description = response.getMessage();
	}
}
