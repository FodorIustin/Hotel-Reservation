package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import reservation.helpers.DBhelper;
import reservation.pojo.Hotel;

public class HotelDao {

	public static ArrayList<Hotel> getAllHotels() throws SQLException {
	    Connection conn = DBhelper.getConnection();
	    String query = "SELECT * FROM hotels";
	    PreparedStatement ps = conn.prepareStatement(query);
	    ResultSet rs = ps.executeQuery();
	    ArrayList<Hotel> hotels = new ArrayList<>();
	    while (rs.next()) {
	        Hotel hotel = new Hotel(
	                rs.getInt("hotel_id"),
	                rs.getString("name"),
	                rs.getDouble("latitude"),
	                rs.getDouble("longitude"));
	        hotels.add(hotel);
	        // Log the hotel details
	        System.out.println("Retrieved hotel: " + hotel.getName());
	    }
	    conn.close();
	    return hotels;
	}

}
