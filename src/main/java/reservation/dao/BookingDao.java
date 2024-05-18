package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import reservation.helpers.DBhelper;
import reservation.pojo.Booking;

import java.sql.Date;



public class BookingDao {
    public static boolean insertBooking(Booking booking) throws SQLException {
        Connection conn = DBhelper.getConnection();
        String query = "INSERT INTO bookings (room_id, user_id, check_in_date, check_out_date) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, booking.getRoomId());
        ps.setDate(2, Date.valueOf(booking.getCheckInDate()));
        ps.setDate(3, Date.valueOf(booking.getCheckOutDate()));
        
        int rowsAffected = ps.executeUpdate();
        conn.close();
        
        return rowsAffected > 0;
    }

    public static boolean updateRoomAvailability(int roomId, boolean isAvailable) throws SQLException {
        Connection conn = DBhelper.getConnection();
        String query = "UPDATE rooms SET is_available = ? WHERE room_id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setBoolean(1, isAvailable);
        ps.setInt(2, roomId);

        int rowsAffected = ps.executeUpdate();
        conn.close();

        return rowsAffected > 0;
    }
}
