package resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import models.BetweenCities;
import services.CityService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<BetweenCities> listCities() {
		CityService cityService = new CityService();
		Iterable<BetweenCities> distances =  cityService.getDistanceBetweenCities();
		return distances;
	}
}
	