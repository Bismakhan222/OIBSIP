# OIBSIP
# Online Reservation System

## Project Overview

The Online Reservation System is a Java-based GUI application developed as part of the Oasis Infobyte Internship.

The system allows users to log in, book tickets, and cancel existing reservations using a PNR number. The application uses Java Swing for the graphical user interface and MySQL for storing reservation data.

## Features

- User Login
- Username and password validation
- Book a ticket
- Generate PNR number
- Store reservation details in MySQL
- Search reservation using PNR
- Cancel reservation
- Display booking and cancellation messages
- Java Swing GUI
- JDBC database connectivity

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- MySQL Connector/J

## Project Structure

```text
src/
├── Main.java
├── Online_Reservation.java
├── Reservation.java
└── Cancellation.java

```

`DBConnection.java` is used locally for database connectivity and is excluded from GitHub because it contains database credentials.

## Database

The project uses a MySQL database named:

```text
reservation_system

```

The reservation data is stored in the MySQL database and can be viewed using SQL queries.

## How to Run

1. Install Java JDK.
2. Install MySQL.
3. Create the `reservation_system` database.
4. Add MySQL Connector/J to the Java project.
5. Configure the database connection locally.
6. Run `Online_Reservation.java`.
7. Login using the configured credentials.
8. Use the menu to book or cancel a ticket.

## Login

For demonstration purposes, the application currently uses:

text
Username: admin
Password: 1234



## Security Note

Database credentials are not included in this GitHub repository. The database connection file is excluded using `.gitignore`.

## Internship

This project was developed as part of the Oasis Infobyte Internship Program.

## Author

Bisma panhwer
