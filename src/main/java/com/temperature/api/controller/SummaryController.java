package com.temperature.api.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temperature.api.models.WeatherResponse;
import com.temperature.api.models.WeatherSchema;
import com.temperature.api.service.SummaryService;

@RestController
@RequestMapping(value="/analyze")
public class SummaryController {
	
	@Autowired
	SummaryService sum;
	
	@GetMapping(value ="summary")
	public ResponseEntity<WeatherResponse> getAlert() throws IOException, ParseException{
		//sum.compareWeather();
		WeatherResponse response = sum.compareWeather();
		return new ResponseEntity<WeatherResponse>(response, HttpStatus.OK);
		//return new ResponseEntity<>(null, null)
	}
	

}
