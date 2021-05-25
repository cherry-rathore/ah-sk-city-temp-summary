package com.temperature.api.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({
	"warmer",
	"wetter",
	"windier",
	"cityWeather"
})
public class WeatherResponse {
	
	@JsonProperty("warmer")
	private  String warmer=null;
	
	@JsonProperty("wetter")
	private  String wetter=null;
	
	@JsonProperty("windier")
	private  String windier=null;
	
	@JsonProperty("cityWeather")
	private  List<WeatherSchema> cityWeather = null;

	public String getWarmer() {
		return warmer;
	}

	public void setWarmer(String warmer) {
		this.warmer = warmer;
	}

	public String getWetter() {
		return wetter;
	}

	public void setWetter(String wetter) {
		this.wetter = wetter;
	}

	public String getWindier() {
		return windier;
	}

	public void setWindier(String windier) {
		this.windier = windier;
	}


	public List<WeatherSchema> getCityWeather() {
		return cityWeather;
	}

	public void setCityWeather(List<WeatherSchema> cityWeather) {
		this.cityWeather = cityWeather;
	}
	
	

}
