package reservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import reservation.helpers.DBhelper;
import reservation.pojo.Feedback;

public class FeedbackDao {
    public static boolean insertFeedback(Feedback feedback) throws SQLException {
        Connection conn = DBhelper.getConnection();
        String query = "INSERT INTO feedback (hotel_id, user_id, rating, comments) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, feedback.getHotelId());
        ps.setInt(2, feedback.getUserId());
        ps.setInt(3, feedback.getRating());
        ps.setString(4, feedback.getComments());
        
        int rowsAffected = ps.executeUpdate();
        conn.close();
        
        return rowsAffected > 0;
    }
}
