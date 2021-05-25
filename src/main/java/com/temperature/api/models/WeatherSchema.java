package com.temperature.api.models;

public class WeatherSchema {
	
	Integer humidity;
	
	Float temp;
	
	
	Long epochDt;
		
	String LocalDateTime;
	
	String CityName;
	
	String lat;
	
	String lon;
	
	String timezone;

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getCityName() {
		return CityName;
	}

	public void setCityName(String cityName) {
		CityName = cityName;
	}

	public String getLocalDateTime() {
		return LocalDateTime;
	}

	public void setLocalDateTime(String localDateTime) {
		LocalDateTime = localDateTime;
	}

	Float windSpeed;

	public Float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Long getEpochDt() {
		return epochDt;
	}

	public void setEpochDt(Long epochDt) {
		this.epochDt = epochDt;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Float getTemp() {
		return temp;
	}

	public void setTemp(Float temp) {
		this.temp = temp;
	}
	

}
