package com.amazon.util;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequirements {
	
	@NotNull
	private String pincode;
	@NotNull
	private Double radius;
	private String category;
}
