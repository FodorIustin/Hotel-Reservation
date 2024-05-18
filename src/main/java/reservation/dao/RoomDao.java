package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import reservation.helpers.DBhelper;
import reservation.pojo.Room;

public class RoomDao {

   
    public static ArrayList<Room> getRoomsByHotelId(int hotelId) throws SQLException {
        Connection conn = DBhelper.getConnection();
        String query = "SELECT * FROM rooms WHERE hotel_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, hotelId);
        ResultSet rs = ps.executeQuery();
        ArrayList<Room> rooms = new ArrayList<>();
        while (rs.next()) {
            Room room = new Room(
                rs.getInt("hotel_id"),
                rs.getString("room_number"),
                rs.getString("type"),
                rs.getDouble("price"),
                rs.getInt("is_available")
            );
            rooms.add(room);
        }
        conn.close();
        return rooms;
    }
}
