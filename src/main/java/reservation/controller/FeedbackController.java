package reservation.controller;

import java.sql.SQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import reservation.dao.FeedbackDao;
import reservation.pojo.Feedback;

@Controller
public class FeedbackController {
	@PostMapping("/submitFeedback")
	public ModelAndView submitFeedbackFromAllReservations(
	        @RequestParam("hotelId") int hotelId,
	        @RequestParam("userId") int userId,
	        @RequestParam("rating") int rating,
	        @RequestParam("comments") String comments) {

	    Feedback feedback = new Feedback();
	    feedback.setHotelId(hotelId);
	    feedback.setUserId(userId);
	    feedback.setRating(rating);
	    feedback.setComments(comments);
	    
	    try {
	        boolean feedbackInserted = FeedbackDao.insertFeedback(feedback);
	        if (feedbackInserted) {
	            return new ModelAndView("allreservations").addObject("success", true);
	        } else {
	            return new ModelAndView("allreservations").addObject("failure", true);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ModelAndView("error");
	    }
	}

}
