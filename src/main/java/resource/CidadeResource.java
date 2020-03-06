package resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dao.CityDao;
import models.City;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	CityDao cityDao = new CityDao();
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<City> listCities() {
		Iterable<City> cities =  cityDao.findAll();
		
		//dist = (6371*acos(cos(pi()*(90 - (LAT_DESTINO))/180)* cos((90 - (LAT_ORIGEM))* pi()/180)+ sin((90 - (-LAT_DESTINO))* pi()/180)* si((90 - (LAT_ORIGEM))*pi()/180)* cos((LON_ORIGEM - (LON_DESTINO))*pi()/180)));
		
		return cities;
	}
}
	