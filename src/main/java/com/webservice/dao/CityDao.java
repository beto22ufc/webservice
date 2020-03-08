package com.webservice.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Repository;

import com.webservice.dao.connection.ConnectionMysql;
import com.webservice.model.City;

@Repository
public class CityDao {
	
	private final static Logger LOGGER = Logger.getLogger(CityDao.class.getName());

	public CityDao() {
		
    }
	
	public City insert (City city) {
		String sql = "INSERT INTO city (name, latitude, longitude) VALUES (?,?,?)";		
		try {
            PreparedStatement stmt = ConnectionMysql.getConectionMysql().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);           
            stmt.setString(1,city.getName());
            stmt.setFloat(2,city.getLatitude());
            stmt.setFloat(3,city.getLongitude());
            stmt.executeUpdate();
            stmt.close();
            return city;
        } catch (SQLException e) {
        	e.printStackTrace();
        	return null;
        }
	}
	
	public List<City> findAll () {
		String sql = "SELECT * FROM city";
		List<City> cities = new ArrayList<City>();
		try {
			PreparedStatement stmt = ConnectionMysql.getConectionMysql().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();			
			while(rs.next()) {
				City city = new City();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setLatitude(rs.getFloat("latitude"));
				city.setLongitude(rs.getFloat("longitude"));
				
				cities.add(city);
			}
			rs.close();
            stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
        }
		return cities;
		
	}
	
}
