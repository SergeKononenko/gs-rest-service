package hello;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelayTestEndpoint {

	@RequestMapping(value = "/rest/json/rest-relay/BuildingControls/api/readCurrentSettings", method = RequestMethod.POST)
	public String mockReadCurrentSettings(@RequestBody String jsonObj) {
		
		System.out.println(jsonObj);
		
		return jsonObj;
	}
	
	@RequestMapping(value = "/rest/json/rest-relay/BuildingControls/api/setCurrentSettings", method = RequestMethod.POST)
	public String mockSetCurrentSettings(@RequestBody String jsonObj) {
		
		System.out.println(jsonObj);
		
		return jsonObj;
	}
}
