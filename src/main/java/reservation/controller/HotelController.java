package reservation.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import reservation.dao.HotelDao;
import reservation.dao.RoomDao;
import reservation.dao.LocationConverter;
import reservation.pojo.Hotel;
import reservation.pojo.Room;

@Controller
public class HotelController {

    @GetMapping("/reservations/all")
    public ModelAndView listAll() throws SQLException {
        ArrayList<Hotel> hotels = HotelDao.getAllHotels();
        ModelAndView mv = new ModelAndView("allreservations");
        mv.addObject("hotels", hotels);
        return mv;
    }

    @PostMapping("/reservations/nearby")
    public ModelAndView findNearby(
            @RequestParam("userLatitude") double userLatitude,
            @RequestParam("userLongitude") double userLongitude,
            @RequestParam("radius") double radius) throws SQLException {

        List<Hotel> allHotels = HotelDao.getAllHotels();
        List<Hotel> nearbyHotels = new ArrayList<>();

        for (Hotel hotel : allHotels) {
            double distance = LocationConverter.calculateEuclideanDistance(userLatitude, userLongitude, hotel.getLatitude(), hotel.getLongitude());
            if (distance <= radius) {
                hotel.setDistance(distance);
                List<Room> rooms = RoomDao.getRoomsByHotelId(hotel.getHotelId());
                hotel.setRooms(rooms);
                nearbyHotels.add(hotel);
            }
        }

        ModelAndView mv = new ModelAndView("allreservations");
        mv.addObject("hotels", nearbyHotels);
        return mv;
    }
}
