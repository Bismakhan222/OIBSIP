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



## Task 2 — Number Guessing Game

**### Project Overview**

The Number Guessing Game is a Java console-based game developed as part of the Oasis Infobyte Internship.

The computer generates a random number between 1 and 100, and the player attempts to guess the number within a maximum number of attempts. After each guess, the game provides a hint indicating whether the guess is too high or too low.

### Features

* Random number generation from 1 to 100
* User input using Scanner
* Too High / Too Low hints
* Correct answer notification
* Attempt counter
* Maximum 10 attempts per round
* Input validation
* Hint for even or odd number
* Play Again option
* Multiple round support
* Game statistics
* Average attempts tracking

### Technologies Used

* Java
* Random
* Scanner
* while loops
* if-else statements
* Exception handling

### How to Run

1. Open the project in IntelliJ IDEA.
2. Open `src/NumberGuessingGame.java`.
3. Run the `main()` method.
4. Enter a number between 1 and 100.
5. Follow the hints until you guess the correct number.
6. Choose `yes` or `no` when asked to play again.

### File

```text
src/
└── NumberGuessingGame.java
```

---

