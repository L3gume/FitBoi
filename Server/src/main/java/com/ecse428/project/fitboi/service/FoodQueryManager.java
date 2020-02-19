package com.ecse428.project.fitboi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ecse428.project.fitboi.model.DBFood;

public class FoodQueryManager
{
    private static final String url = "jdbc:postgresql://ec2-3-224-165-85.compute-1.amazonaws.com:5432/d76g5sbjvh9r1j?user=fiegbkuvdmapic&password=78ed246c6344c6feca9e0543045043b4bb8a2efac2682cc11b7930c23165d237&sslmode=require";
    
    private static Connection connect() throws SQLException
    {
        return DriverManager.getConnection(url);
    }

    public static List<DBFood> getDBFoodsWithName(String name)
    {
        List<DBFood> foods = new ArrayList<DBFood>();
        Connection conn;
        try {
            conn = connect();
            
            String sql = "SELECT * FROM food WHERE name like '%".concat(name).concat("%'");
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                DBFood cur_food = new DBFood(rs.getString("name"),
                    rs.getLong("cal"), rs.getDouble("protein"), 
                    rs.getDouble("fat"), rs.getDouble("carbs"));

                foods.add(cur_food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return foods;
    }
}
