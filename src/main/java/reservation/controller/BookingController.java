package reservation.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import reservation.dao.BookingDao;
import reservation.pojo.Booking;

@Controller
public class BookingController {

    @PostMapping("/bookRoom")
    public ModelAndView bookRoom(
            @RequestParam("roomId") int roomId,
            @RequestParam("userId") int userId,
            @RequestParam("checkInDate") String checkInDate,
            @RequestParam("checkOutDate") String checkOutDate) {

        Booking booking = new Booking(roomId, userId, LocalDate.parse(checkInDate), LocalDate.parse(checkOutDate));
        try {
            boolean bookingInserted = BookingDao.insertBooking(booking);
            if (bookingInserted) {
                // Update room availability
                BookingDao.updateRoomAvailability(roomId, false);
                return new ModelAndView("bookingSuccess");
            } else {
                return new ModelAndView("bookingFailure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new ModelAndView("error");
        }
    }
}
