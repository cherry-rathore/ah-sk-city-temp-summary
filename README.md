# ah-sk-city-temp-summary

+ Local Endpoint - http://localhost:8090/analyze/summary
+ Parameters assuming for -
+ Warmer : City1 Temperature > City2 Temperature i.e. city1 is warm
+ Windier : City1 wind speed > City2 wind speed i.e. city1 is windier
+ Wetters: City1 Humidity > City2 Hunidity i.e. city1 is wetter
+ Units : Metric
+ Temperature - Celsius
+ Speed - metres per second
+ Humidity - Percentage
  
+ Sample Response - 

  {
    "warmer": "Ahmedabad",
    "wetter": "Skibbereen",
    "windier": "Skibbereen",
    "cityWeather": [
        {
            "humidity": 59,
            "temp": 34.02,
            "epochDt": 1621789848,
            "lat": "23.02",
            "lon": "72.57",
            "timezone": "Asia/Kolkata",
            "windSpeed": 3.6,
            "localDateTime": "2021-05-23 10:40:48",
            "cityName": null
        },
        {
            "humidity": 75,
            "temp": 11.86,
            "epochDt": 1621789849,
            "lat": "51.5559",
            "lon": "-9.2621",
            "timezone": "Europe/Dublin",
            "windSpeed": 8.91,
            "localDateTime": "2021-05-23 06:10:49",
            "cityName": null
        }
    ]
}
