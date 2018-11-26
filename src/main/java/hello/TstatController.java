package hello;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TstatController {
	
	private CurrentReading reading = new CurrentReading(0, 0, "zoneId", 0, "zoneName", "buildingId", "twCustomHeader1");

	@RequestMapping(value = "/getTemp", method = RequestMethod.POST) // defaults to GET
	public CurrentReading getTemp(@RequestBody GetTempRequest getTempReq) {

		WebRequest.readCurrentSettings("http://10.1.5.117:8080", "/rest/json/rest-relay/BuildingControls/api/readCurrentSettings", getTempReq);	
		
		synchronized (reading) {
			try {
				reading.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return reading;
	} 
	
	@PostMapping(value = "/setTemp")
	public String setTemp(@RequestBody SetTempRequest tsds) {

		return WebRequest.setCurrentSettings("http://10.1.5.117:8080", "/rest/json/rest-relay/BuildingControls/api/setCurrentSettings", tsds); //Need to change URL;

	}

	@PostMapping("/storeCurrentReadings") // Storing Data inside the App from Phub_demo
	public Response storeReadings(@RequestBody String stringToParse,
							  	@RequestHeader(value="X-API-KEY") String twCustomHeader1) {

		double setTemperature;

		try {
			JSONArray jsonarray = new JSONArray(stringToParse);
			JSONObject jsonObj = jsonarray.getJSONObject(0);

			setTemperature = (double) jsonObj.getDouble("setTemperature");
			
			synchronized (reading) {
				reading.setSetTemperature(setTemperature);
				reading.notifyAll();
			}
			
			return new Response("Ok!");
		} catch (JSONException e) {
			e.printStackTrace();
			return new Response("Failure!");
		}

	}
	
}

/**********************
 * sample
 * 
 * [ { "setTemperatureMin":18.3, "setTemperatureMax":33.3,
 * "zoneId":"MasseyHall", "setTemperature":22.2, "zoneName":"Large Boardroom –
 * Massey Hall", "buildingId":"156King",
 * "twCustomHeader1":"WhaknAgApL5YXu1IEondS1A1hnObH0Rw2IbaB2lm" } ] List<Car>
 ***********************/