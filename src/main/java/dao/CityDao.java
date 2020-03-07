package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import connection.ConnectionMysql;
import models.City;

public class CityDao {
	
	private final static Logger LOGGER = Logger.getLogger(CityDao.class.getName());

	public CityDao() {
		
    }
	
	public void insert (City city) {
		String sql = "INSERT INTO city (name, latitude, longitude) VALUES (?,?,?)";		
		try {
            PreparedStatement stmt = ConnectionMysql.getConectionMysql().prepareStatement(sql);
            stmt.setString(1,city.getName());
            stmt.setFloat(2,city.getLatitude());
            stmt.setFloat(3,city.getLongitude());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
        	LOGGER.log(LOGGER.getLevel(), "Error accessing the database...", e.fillInStackTrace());
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
			LOGGER.log(LOGGER.getLevel(), "Error accessing the database...", e.fillInStackTrace());
        }
		return cities;
		
	}
	
}
