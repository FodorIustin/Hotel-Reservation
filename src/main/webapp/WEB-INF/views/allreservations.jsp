<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Hotels Nearby</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script>
        function getUserLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(showPosition, showError);
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }

        function showPosition(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            document.getElementById("userLatitude").value = latitude;
            document.getElementById("userLongitude").value = longitude;
            document.getElementById("location").innerHTML = "Latitude: " + latitude + "<br>Longitude: " + longitude;
        }

        function showError(error) {
            switch(error.code) {
                case error.PERMISSION_DENIED:
                    alert("User denied the request for Geolocation.");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("Location information is unavailable.");
                    break;
                case error.TIMEOUT:
                    alert("The request to get user location timed out.");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("An unknown error occurred.");
                    break;
            }
        }
    </script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <h1 class="text-3xl font-bold mb-4">Find Nearby Hotels</h1>
        <p>Click the button below to retrieve your current location:</p>
        <button onclick="getUserLocation()" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4">Get Location</button>
        <p id="location" class="mt-4"></p>

        <form action="${pageContext.request.contextPath}/reservations/nearby" method="post" class="mt-8">
            <input type="hidden" id="userLatitude" name="userLatitude" />
            <input type="hidden" id="userLongitude" name="userLongitude" />
            <label for="radius" class="block font-semibold">Radius (km):</label>
            <input type="text" id="radius" name="radius" class="border border-gray-300 px-4 py-2 rounded mt-2 focus:outline-none focus:border-blue-500" />
            <button type="submit" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mt-4">Find Hotels</button>
        </form>

        <c:if test="${not empty hotels}">
            <h2 class="text-2xl font-bold mt-8">Nearby Hotels</h2>
            <table class="mt-4 w-full">
                <thead>
                    <tr>
                        <th class="border px-4 py-2">Id</th>
                        <th class="border px-4 py-2">Name</th>
                        <th class="border px-4 py-2">Latitude</th>
                        <th class="border px-4 py-2">Longitude</th>
                        <th class="border px-4 py-2">Distance (km)</th>
                        <th class="border px-4 py-2">Rooms</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="hotel" items="${hotels}">
                        <tr>
                            <td class="border px-4 py-2"><c:out value="${hotel.hotelId}"/></td>
                            <td class="border px-4 py-2"><c:out value="${hotel.name}"/></td>
                            <td class="border px-4 py-2"><c:out value="${hotel.latitude}"/></td>
                            <td class="border px-4 py-2"><c:out value="${hotel.longitude}"/></td>
                            <td class="border px-4 py-2"><c:out value="${hotel.distance}"/></td>
                            <td class="border px-4 py-2">
                                <c:forEach var="room" items="${hotel.rooms}">
                                    <form action="${pageContext.request.contextPath}/bookRoom" method="post">
                                        <input type="hidden" name="roomId" value="${room.roomId}" />
                                        <input type="hidden" name="hotelId" value="${hotel.hotelId}" />
                                        
                                        <p>Room Number: <c:out value="${room.roomNumber}"/> - Type: <c:out value="${room.type}"/> - Price: <c:out value="${room.price}"/></p>
                                        <label for="checkInDate">Check-in Date:</label>
                                        <input type="date" name="checkInDate" required class="border border-gray-300 px-4 py-2 rounded focus:outline-none focus:border-blue-500" />
                                        <label for="checkOutDate">Check-out Date:</label>
                                        <input type="date" name="checkOutDate" required class="border border-gray-300 px-4 py-2 rounded focus:outline-none focus:border-blue-500" />
                                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4">Book Room</button>
                                    </form>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        
        <h2 class="text-2xl font-bold mt-8">Provide Feedback</h2>
        <form action="${pageContext.request.contextPath}/submitFeedback" method="post" class="mt-4">
            <input type="hidden" name="hotelId" value="1" /> <!-- Replace with hotelId value -->
            <input type="hidden" name="userId" value="1" /> <!-- Replace with userId value -->
            <label for="rating" class="block font-semibold">Rating:</label>
            <input type="number" name="rating" id="rating" min="1" max="5" required class="border border-gray-300 px-4 py-2 rounded mt-2 focus:outline-none focus:border-blue-500" />
            <label for="comments" class="block font-semibold mt-4">Comments:</label>
            <textarea name="comments" id="comments" rows="4" cols="50" required class="border border-gray-300 px-4 py-2 rounded mt-2 focus:outline-none focus:border-blue-500"></textarea>
            <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mt-4">Submit Feedback</button>
        </form>
