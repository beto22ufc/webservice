package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMysql;
import models.City;

public class CityDao {
	private Connection connectionMysql;

	public CityDao() {
        this.connectionMysql = new ConnectionMysql().getConectionMysql();
    }
	
	public void insert (City city) {
		String sql = "insert into city (name, latitude, longitude) values (?,?,?)";
		
		try {
            PreparedStatement stmt = connectionMysql.prepareStatement(sql);

            stmt.setString(1,city.getName());
            stmt.setFloat(2,city.getLatitude());
            stmt.setFloat(3,city.getLongitude());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
           System.out.println("Error accessing the database... ");
        }
	}
	
	public List<City> findAll () {
		String sql = "select * from city";
		List<City> cities = new ArrayList<City>();
		try {
			PreparedStatement stmt = connectionMysql.prepareStatement(sql);
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
           System.out.println("Error accessing the database... ");
        }
		return cities;
		
	}
	
}
