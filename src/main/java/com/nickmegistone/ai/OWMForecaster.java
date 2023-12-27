package com.nickmegistone.ai;

import static com.nickmegistone.apputils.AppUtils.getUrlContent;
import io.ipinfo.api.IPinfo;
import io.ipinfo.api.errors.RateLimitedException;
import io.ipinfo.api.model.IPResponse;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Locale;

/**
 * This class represents an OWMForecaster that retrieves weather forecast information from the OpenWeatherMap API.
 *
 * @author Mykyta Kyselov - <a href="https://github.com/TheMegistone4Ever">Github</a>
 */
public class OWMForecaster {

    private final String OWMId;
    private final IPResponse response;

    /**
     * Constructs an OWMForecaster object with the specified IPInfo ID and OpenWeatherMap ID.
     *
     * @param IPInfoId The IPInfo ID for retrieving location information.
     * @param OWMId    The OpenWeatherMap ID for accessing the weather API.
     */
    public OWMForecaster(String IPInfoId, String OWMId) {
        this.OWMId = OWMId;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com/").openStream()))) {
            response = new IPinfo.Builder().setToken(IPInfoId).build().lookupIP(br.readLine());
        } catch (IOException | RateLimitedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the weather forecast for the current location.
     *
     * @return The weather forecast as a formatted string.
     */
    public String forecast() {
        String forecast = "Hello there! It seems we've encountered a little hiccup in our weather system, and " +
                "unfortunately, we don't have your specific location information at the moment.";
        JSONObject json = new JSONObject(
                getUrlContent(
                        String.format(
                                "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&appid=%s&units=%s&lang=%s",
                                response.getLatitude(),
                                response.getLongitude(),
                                OWMId,
                                "metric",
                                "en"
                        )
                )
        );
        if (json.getString("cod").equals("200")) {
            JSONObject infoObj = json.getJSONArray("list").getJSONObject(1);
            JSONObject mainObj = infoObj.getJSONObject("main");
            JSONObject cityObj = json.getJSONObject("city");
            forecast = String.format(
                    "Good evening, folks! It's time for your weather update. In the beautiful town of %s, located " +
                            "in %s at coordinates %,.1f latitude and %,.1f longitude, we're experiencing next " +
                            "conditions. Currently, the temperature is %,.1f degrees Celsius, with a feels-like " +
                            "temperature of %,.1f degrees Celsius. We're expecting a maximum temperature of %,.1f " +
                            "degrees Celsius and a minimum temperature of %,.1f degrees Celsius. The atmospheric " +
                            "pressure stands at %d millibars, and the humidity is %d%%. As for the skies, %s. The " +
                            "wind is blowing at a speed of %,.1f meters per second. That's all for now from your " +
                            "weather team. Stay tuned for more updates!",
                    cityObj.getString("name"),
                    new Locale.Builder().setRegion(cityObj.getString("country")).build().getDisplayCountry(Locale.ENGLISH),
                    Double.parseDouble(response.getLatitude()),
                    Double.parseDouble(response.getLongitude()),
                    mainObj.getDouble("temp"),
                    mainObj.getDouble("feels_like"),
                    mainObj.getDouble("temp_max"),
                    mainObj.getDouble("temp_min"),
                    mainObj.getInt("pressure"),
                    mainObj.getInt("humidity"),
                    infoObj.getJSONArray("weather").getJSONObject(0).getString("description"),
                    infoObj.getJSONObject("wind").getDouble("speed")
            );
        }
        return forecast;
    }
}
