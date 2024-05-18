package reservation.pojo;

import java.util.List;

public class Hotel {
    private int hotel_id;
    private String name;
    private double latitude;
    private double longitude;
    private double distance; 
    private List<Room> rooms; 

    public Hotel() {}

    public Hotel(int hotel_id, String name, double latitude, double longitude) {
    	this.hotel_id = hotel_id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and setters
    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotelId) {
        this.hotel_id = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
