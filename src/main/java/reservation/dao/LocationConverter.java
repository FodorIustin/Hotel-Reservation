package reservation.dao;

public class LocationConverter {

    // Convert latitude from decimal degrees to meters
    public static double convertLatitudeToMeters(double latitude) {
        final double METERS_PER_DEGREE_LATITUDE = 111000; 
        return latitude * METERS_PER_DEGREE_LATITUDE;
    }

    // Convert longitude from decimal degrees to meters
    public static double convertLongitudeToMeters(double longitude, double latitude) {
        final double METERS_PER_DEGREE_LONGITUDE_AT_EQUATOR = 111320; 
        double cosLatitude = Math.cos(Math.toRadians(latitude));
        return longitude * METERS_PER_DEGREE_LONGITUDE_AT_EQUATOR * cosLatitude;
    }

    // Calculate the distance between two points 
    public static double calculateEuclideanDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Meters = convertLatitudeToMeters(lat1);
        double lon1Meters = convertLongitudeToMeters(lon1, lat1);
        double lat2Meters = convertLatitudeToMeters(lat2);
        double lon2Meters = convertLongitudeToMeters(lon2, lat2);
        
        return Math.sqrt(Math.pow(lat2Meters - lat1Meters, 2) + Math.pow(lon2Meters - lon1Meters, 2)) / 1000; // Distance in kilometers
    }
}
