package com.temperature.api.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.BiPredicate;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.temperature.api.models.WeatherResponse;
import com.temperature.api.models.WeatherSchema;
@Service
public class SummaryService {
	
	private final String APIURL = "https://api.openweathermap.org/data/2.5/onecall";
	
	private final String appId = "d78f1e372f10817b6e131c38bd605ed7";
		
	private final String ahmedabadLat = "23.02";
	
	private final String ahmedabadLong = "72.57";
	
	private final String skibbereenLat = "51.5559&";
	
	private final String skibbereenLong = "-9.2621";
	
	private final String units = "metric";
	
	enum Cities
	{
	    Ahmedabad, Skibbereen;
	}
	
	ObjectMapper mapper = new ObjectMapper();
	
	 final HttpClient client = new HttpClient();
	 
	 public WeatherResponse compareWeather() throws IOException, ParseException {
		 
		 WeatherSchema ahmedabadWeather = getAhmedbabProperties();
		 
		 WeatherSchema skibbereenWeather = getSkibbereenProperties();
		 
		 ahmedabadWeather.getHumidity().compareTo(skibbereenWeather.getHumidity());
		 
		 ahmedabadWeather.getTemp().compareTo(skibbereenWeather.getTemp());
		 		 
		 
		 BiPredicate<Float, Float> warmer = (i,j) -> {
			 return i>j;
		 };
		 
		 BiPredicate<Integer, Integer> wetter = (i,j) -> {
			 return i>j;
		 };
		 
		 BiPredicate<Float, Float> windier = (i,j) -> {
			 return i>j;
		 };
		 
		 WeatherResponse res = new WeatherResponse();
		 
		 res.setWarmer(warmer.test(ahmedabadWeather.getTemp(), skibbereenWeather.getTemp())?Cities.Ahmedabad.toString():Cities.Skibbereen.toString());
		 
		 res.setWetter(wetter.test(ahmedabadWeather.getHumidity(), skibbereenWeather.getHumidity())?Cities.Ahmedabad.toString():Cities.Skibbereen.toString());
		 
		 res.setWindier(windier.test(ahmedabadWeather.getWindSpeed(), skibbereenWeather.getWindSpeed())?Cities.Ahmedabad.toString():Cities.Skibbereen.toString());
		 
		 List<WeatherSchema> cityWet = List.of(ahmedabadWeather,skibbereenWeather);
		 
		 res.setCityWeather(cityWet);
		 
		 return res;
		 
	 }
	
	 public WeatherSchema getAhmedbabProperties() throws IOException, ParseException {
		 
		final String ahmedabadTempURI  = APIURL+"?lat="+ahmedabadLat+"&lon="+ahmedabadLong+"&exclude=daily"+"&appid="+appId+"&units="+units;
		 
		GetMethod getWeather = new GetMethod(ahmedabadTempURI);
		
		getWeather.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3,false));
		
		getWeather = setHeaders(getWeather);
		client.executeMethod(getWeather);
			
		return setObject(getWeather.getResponseBodyAsString());		
	}
	 
	 public WeatherSchema getSkibbereenProperties() throws IOException, ParseException {
		 
		final String skibbereenURI  = APIURL+"?lat="+skibbereenLat+"&lon="+skibbereenLong+"&exclude=daily"+"&appid="+appId+"&units="+units;
		 
		GetMethod getWeather = new GetMethod(skibbereenURI);
		
		getWeather.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3,false));
		
		getWeather = setHeaders(getWeather);
		client.executeMethod(getWeather);		
		return setObject(getWeather.getResponseBodyAsString());
		
	}
	 
	
	public GetMethod setHeaders(GetMethod api) {
	api.addRequestHeader("user-agent", "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36");
	api.addRequestHeader("accept", "application/json");
	api.addRequestHeader("Content-Type", "application/json");

	return api;
	}
	
	public WeatherSchema setObject(String response) throws JsonParseException, JsonMappingException, IOException, ParseException {
		
		JSONObject getDetails = new JSONObject(response);
		
		JSONObject weather = new JSONObject();
		
		weather.put("temp", getDetails.getJSONObject("current").getFloat("temp"));
		weather.put("humidity", getDetails.getJSONObject("current").getInt("humidity"));
		weather.put("epochDt", getDetails.getJSONObject("current").getLong("dt"));
		weather.put("windSpeed", getDetails.getJSONObject("current").getFloat("wind_speed"));
		weather.put("lat", getDetails.get("lat"));
		weather.put("lon", getDetails.get("lon"));
		weather.put("timezone", getDetails.get("timezone"));
		WeatherSchema weatherObject = mapper.readValue(weather.toString(), WeatherSchema.class);
		
		Date d = new Date(Long.parseLong(weatherObject.getEpochDt().toString()) *1000);		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(weatherObject.getTimezone()));
		weatherObject.setLocalDateTime(sdf.format(d).toString());
				
		return weatherObject;
		
	}
  

}
