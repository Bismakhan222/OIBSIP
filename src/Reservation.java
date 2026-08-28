import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Reservation extends JFrame {

    // ==============================
    // UI COMPONENTS
    // ==============================

    private JTextField passengerField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JComboBox<String> classBox;
    private JTextField dateField;
    private JTextField sourceField;
    private JTextField destinationField;


    // ==============================
    // TRAIN DATABASE
    // ==============================

    private static final Map<String, String> TRAIN_DATABASE = new HashMap<>();

    static {

        TRAIN_DATABASE.put("12345", "Rajdhani Express");
        TRAIN_DATABASE.put("67890", "Shatabdi Express");
        TRAIN_DATABASE.put("11111", "Duronto Express");
        TRAIN_DATABASE.put("22222", "Garib Rath");

    }


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public Reservation() {

        initComponents();
        setupLayout();
        addListeners();

        setTitle("Train Reservation");

        setSize(500, 500);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);
    }


    // ==============================
    // CREATE COMPONENTS
    // ==============================

    private void initComponents() {

        passengerField = new JTextField(20);

        trainNumberField = new JTextField(20);

        trainNameField = new JTextField(20);

        trainNameField.setEditable(false);

        trainNameField.setBackground(Color.LIGHT_GRAY);


        String[] classes = {
                "Economy",
                "Business",
                "AC"
        };

        classBox = new JComboBox<>(classes);


        dateField = new JTextField(20);

        dateField.setToolTipText(
                "Format: dd/MM/yyyy"
        );


        sourceField = new JTextField(20);

        destinationField = new JTextField(20);
    }


    // ==============================
    // GUI LAYOUT
    // ==============================

    private void setupLayout() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(5, 5, 5, 5);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;


        // Passenger Name

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(
                new JLabel("Passenger Name:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                passengerField,
                gbc
        );


        // Train Number

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(
                new JLabel("Train Number:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                trainNumberField,
                gbc
        );


        // Train Name

        gbc.gridx = 0;
        gbc.gridy = 2;

        add(
                new JLabel("Train Name:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                trainNameField,
                gbc
        );


        // Class Type

        gbc.gridx = 0;
        gbc.gridy = 3;

        add(
                new JLabel("Class Type:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                classBox,
                gbc
        );


        // Journey Date

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(
                new JLabel("Journey Date:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                dateField,
                gbc
        );


        // Source

        gbc.gridx = 0;
        gbc.gridy = 5;

        add(
                new JLabel("Source:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                sourceField,
                gbc
        );


        // Destination

        gbc.gridx = 0;
        gbc.gridy = 6;

        add(
                new JLabel("Destination:"),
                gbc
        );

        gbc.gridx = 1;

        add(
                destinationField,
                gbc
        );


        // Book Button

        JButton bookButton =
                new JButton("Book Ticket");

        gbc.gridx = 0;
        gbc.gridy = 7;

        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.CENTER;

        add(
                bookButton,
                gbc
        );


        // Button Action

        bookButton.addActionListener(
                e -> bookTicket()
        );
    }


    // ==============================
    // TRAIN NAME AUTOMATICALLY
    // ==============================

    private void addListeners() {

        trainNumberField
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                updateTrainName();
                            }


                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                updateTrainName();
                            }


                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                updateTrainName();
                            }


                            private void updateTrainName() {

                                String number =
                                        trainNumberField
                                                .getText()
                                                .trim();

                                String name =
                                        TRAIN_DATABASE
                                                .getOrDefault(
                                                        number,
                                                        ""
                                                );

                                trainNameField
                                        .setText(name);
                            }
                        }
                );
    }


    // ==============================
    // BOOK TICKET
    // ==============================

    private void bookTicket() {

        String passenger =
                passengerField
                        .getText()
                        .trim();


        String trainNumber =
                trainNumberField
                        .getText()
                        .trim();


        String trainName =
                trainNameField
                        .getText()
                        .trim();


        String classType =
                (String)
                        classBox
                                .getSelectedItem();


        String date =
                dateField
                        .getText()
                        .trim();


        String source =
                sourceField
                        .getText()
                        .trim();


        String destination =
                destinationField
                        .getText()
                        .trim();


        // ==============================
        // VALIDATION
        // ==============================

        if (
                passenger.isEmpty()
                        ||
                        trainNumber.isEmpty()
                        ||
                        trainName.isEmpty()
                        ||
                        date.isEmpty()
                        ||
                        source.isEmpty()
                        ||
                        destination.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Train number must be numeric

        if (!trainNumber.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Train Number must be numeric.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Source and destination cannot be same

        if (
                source.equalsIgnoreCase(
                        destination
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Source and Destination cannot be the same.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ==============================
        // DATE CONVERSION
        // ==============================

        String mysqlDate;

        try {

            java.text.SimpleDateFormat
                    inputFormat =
                    new java.text.SimpleDateFormat(
                            "dd/MM/yyyy"
                    );

            inputFormat.setLenient(false);


            java.util.Date parsedDate =
                    inputFormat.parse(date);


            java.text.SimpleDateFormat
                    outputFormat =
                    new java.text.SimpleDateFormat(
                            "yyyy-MM-dd"
                    );


            mysqlDate =
                    outputFormat.format(
                            parsedDate
                    );

        } catch (
                java.text.ParseException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date!\nUse dd/MM/yyyy",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // ==============================
        // SQL INSERT
        // ==============================

        String sql =
                "INSERT INTO reservations " +
                        "(passenger_name, train_number, " +
                        "class_type, journey_date, " +
                        "source, destination) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";


        // ==============================
        // CONNECT TO MYSQL
        // ==============================

        try (
                Connection connection =
                        DBConnection.connect();

                PreparedStatement statement =
                        connection.prepareStatement(
                                sql,
                                java.sql.Statement
                                        .RETURN_GENERATED_KEYS
                        )
        ) {


            // Put values into SQL

            statement.setString(
                    1,
                    passenger
            );


            statement.setInt(
                    2,
                    Integer.parseInt(
                            trainNumber
                    )
            );


            statement.setString(
                    3,
                    classType
            );


            statement.setDate(
                    4,
                    java.sql.Date.valueOf(
                            mysqlDate
                    )
            );


            statement.setString(
                    5,
                    source
            );


            statement.setString(
                    6,
                    destination
            );


            // Execute INSERT

            statement.executeUpdate();


            // ==============================
            // GET AUTO-GENERATED PNR
            // ==============================

            ResultSet keys =
                    statement.getGeneratedKeys();


            if (keys.next()) {

                int pnr =
                        keys.getInt(1);


                // ==============================
                // CONFIRMATION
                // ==============================

                JOptionPane.showMessageDialog(
                        this,

                        "Booking Successful!\n\n" +

                                "PNR: " + pnr + "\n" +

                                "Passenger: " +
                                passenger + "\n" +

                                "Train Number: " +
                                trainNumber + "\n" +

                                "Train Name: " +
                                trainName + "\n" +

                                "Class: " +
                                classType + "\n" +

                                "Date: " +
                                date + "\n" +

                                "Source: " +
                                source + "\n" +

                                "Destination: " +
                                destination,

                        "Booking Confirmation",

                        JOptionPane.INFORMATION_MESSAGE
                );
            }


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Booking Failed!\n\n" +
                            e.getMessage(),

                    "Database Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==============================
    // MAIN
    // ==============================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                Reservation::new
        );

    }
}