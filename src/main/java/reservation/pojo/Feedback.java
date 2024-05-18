package reservation.pojo;

public class Feedback {
    private int feedbackId;
    private int hotelId;
    private int userId;
    private int rating;
    private String comments;

    public Feedback() {
    }

    public Feedback(int feedbackId, int hotelId, int userId, int rating, String comments) {
        this.feedbackId = feedbackId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.rating = rating;
        this.comments = comments;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
