Movie Reservation API
# Movie Reservation Backend

A backend API for a movie reservation system built with **Java and Spring Boot**.

## Features

* User registration and login
* Movie management
* ShowTime management
* Movie reservations
* Seat availability
* BCrypt password encryption
* Request/Response DTOs
* Validation
* MySQL database

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven
* Lombok
* BCrypt

## Structure

The project uses a simple layered structure:

* Controller
* Service
* Repository
* DTO
* Model

The Controller handles API requests, the Service handles the main logic, and the Repository handles database operations.

## Reservation

Users can select a movie, showtime, and seat to make a reservation.

Available seats are calculated based on the total seats in the showtime and existing reservations.

## Authentication

User passwords are encrypted with BCrypt before being stored in the database.

## Future Improvements

* JWT authentication
* Multiple-seat reservations
* Role-based authorization
* Unit testing
* React frontend
  
https://roadmap.sh/projects/movie-reservation-system
