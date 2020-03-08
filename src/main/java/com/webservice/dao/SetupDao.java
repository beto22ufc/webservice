package com.webservice.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.webservice.dao.connection.ConnectionMysql;

public class SetupDao {
	
	public SetupDao() {
		
	}
	
	public void run() {
		List<Migrate> migrations = migrateProcess();
		for (Migrate migrate : migrations) {
			if (runMigrate(migrate)) {
				saveMigrate(migrate);
			}
		}
	}
	
	private boolean runMigrate (Migrate migrate) {
		try {
			PreparedStatement stmt = ConnectionMysql.getConectionMysql().prepareStatement(migrate.content);
			stmt.execute();
            stmt.close();
            return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
        }
	}
	
	private void saveMigrate(Migrate migrate) {
		migrate.content = "-- executed=true\n".concat(migrate.content);
        try {
            Files.write(Paths.get(migrate.name), migrate.content.getBytes());
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	private List<Migrate> migrateProcess() {
		List<String> migrations = getAllMigrations();
		List<Migrate> result = new ArrayList<>();
		for (String migrate : migrations) {
			String content = readFile(migrate);
			if (!content.contains("executed=true")) {
				result.add(new Migrate(migrate, content));
			}
		}
		return result;
	}
	
	private List<String> getAllMigrations() {
		List<String> result = new ArrayList<>();
		try (Stream<Path> walk = Files.walk(Paths.get("db/"))) {
			result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());
		} catch (IOException e) {
		}
		return result;
	}
	
	private static String readFile(String filePath) {
	    StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) {
	        stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}

	
	private class Migrate {
	
		String name;
		String content;
		
		public Migrate(String name, String content) {
			this.name = name;
			this.content = content;
		}
	
	}
}
