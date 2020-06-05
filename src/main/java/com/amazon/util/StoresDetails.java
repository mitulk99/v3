package com.amazon.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoresDetails {
	
	private String merchantname;
	private String opentime;
	private String closetime;
	private String address;
	private String rangeofhomedelivery;
	private Double distance;
	private String category;
	
}