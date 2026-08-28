import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cancellation extends JFrame {

    // ==============================
    // UI COMPONENTS
    // ==============================

    private JTextField pnrField;
    private JTextField passengerField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JTextField classField;
    private JTextField dateField;
    private JTextField sourceField;
    private JTextField destinationField;

    private JButton fetchButton;
    private JButton cancelButton;


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public Cancellation() {

        setTitle("Ticket Cancellation");

        setSize(550, 550);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        createComponents();

        setVisible(true);
    }


    // ==============================
    // CREATE GUI
    // ==============================

    private void createComponents() {

        setLayout(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(7, 7, 7, 7);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // ==============================
        // PNR
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 0;

        add(
                new JLabel("PNR Number:"),
                gbc
        );


        pnrField =
                new JTextField(15);

        gbc.gridx = 1;

        add(
                pnrField,
                gbc
        );


        fetchButton =
                new JButton("Fetch");

        gbc.gridx = 2;

        add(
                fetchButton,
                gbc
        );


        // ==============================
        // PASSENGER
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 1;

        add(
                new JLabel("Passenger Name:"),
                gbc
        );


        passengerField =
                new JTextField(20);

        passengerField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                passengerField,
                gbc
        );


        // ==============================
        // TRAIN NUMBER
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        add(
                new JLabel("Train Number:"),
                gbc
        );


        trainNumberField =
                new JTextField(20);

        trainNumberField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                trainNumberField,
                gbc
        );


        // ==============================
        // TRAIN NAME
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        add(
                new JLabel("Train Name:"),
                gbc
        );


        trainNameField =
                new JTextField(20);

        trainNameField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                trainNameField,
                gbc
        );


        // ==============================
        // CLASS
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;

        add(
                new JLabel("Class Type:"),
                gbc
        );


        classField =
                new JTextField(20);

        classField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                classField,
                gbc
        );


        // ==============================
        // DATE
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;

        add(
                new JLabel("Journey Date:"),
                gbc
        );


        dateField =
                new JTextField(20);

        dateField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                dateField,
                gbc
        );


        // ==============================
        // SOURCE
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;

        add(
                new JLabel("Source:"),
                gbc
        );


        sourceField =
                new JTextField(20);

        sourceField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                sourceField,
                gbc
        );


        // ==============================
        // DESTINATION
        // ==============================

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;

        add(
                new JLabel("Destination:"),
                gbc
        );


        destinationField =
                new JTextField(20);

        destinationField.setEditable(false);

        gbc.gridx = 1;
        gbc.gridwidth = 2;

        add(
                destinationField,
                gbc
        );


        // ==============================
        // CANCEL BUTTON
        // ==============================

        cancelButton =
                new JButton("Confirm Cancellation");

        cancelButton.setEnabled(false);

        gbc.gridx = 0;
        gbc.gridy = 8;

        gbc.gridwidth = 3;

        gbc.fill =
                GridBagConstraints.NONE;

        add(
                cancelButton,
                gbc
        );


        // ==============================
        // BUTTON ACTIONS
        // ==============================

        fetchButton.addActionListener(
                e -> fetchBooking()
        );


        cancelButton.addActionListener(
                e -> cancelBooking()
        );
    }


    // ==============================
    // FETCH BOOKING
    // ==============================

    private void fetchBooking() {

        String pnrText =
                pnrField.getText().trim();


        // Check empty PNR

        if (pnrText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a PNR number.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        // Check numeric PNR

        if (!pnrText.matches("\\d+")) {

            JOptionPane.showMessageDialog(
                    this,
                    "PNR must be numeric.",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }


        int pnr =
                Integer.parseInt(pnrText);


        // ==============================
        // SQL QUERY
        // ==============================

        String sql =
                "SELECT r.*, t.train_name " +
                        "FROM reservations r " +
                        "JOIN trains t " +
                        "ON r.train_number = t.train_number " +
                        "WHERE r.pnr = ?";


        try {

            Connection connection =
                    DBConnection.connect();


            if (connection == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            PreparedStatement statement =
                    connection.prepareStatement(sql);


            statement.setInt(
                    1,
                    pnr
            );


            ResultSet result =
                    statement.executeQuery();


            // ==============================
            // BOOKING FOUND
            // ==============================

            if (result.next()) {

                passengerField.setText(
                        result.getString(
                                "passenger_name"
                        )
                );


                trainNumberField.setText(
                        result.getString(
                                "train_number"
                        )
                );


                trainNameField.setText(
                        result.getString(
                                "train_name"
                        )
                );


                classField.setText(
                        result.getString(
                                "class_type"
                        )
                );


                dateField.setText(
                        result.getString(
                                "journey_date"
                        )
                );


                sourceField.setText(
                        result.getString(
                                "source"
                        )
                );


                destinationField.setText(
                        result.getString(
                                "destination"
                        )
                );


                cancelButton.setEnabled(true);


                JOptionPane.showMessageDialog(
                        this,
                        "Booking found!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No booking found with PNR: "
                                + pnr,
                        "Not Found",
                        JOptionPane.ERROR_MESSAGE
                );


                clearFields();
            }


            result.close();

            statement.close();

            connection.close();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==============================
    // CANCEL BOOKING
    // ==============================

    private void cancelBooking() {

        String pnrText =
                pnrField.getText().trim();


        int pnr =
                Integer.parseInt(pnrText);


        // ==============================
        // CONFIRMATION DIALOG
        // ==============================

        int choice =
                JOptionPane.showConfirmDialog(

                        this,

                        "Are you sure you want to cancel\n"
                                + "this booking?",

                        "Confirm Cancellation",

                        JOptionPane.YES_NO_OPTION
                );


        if (choice != JOptionPane.YES_OPTION) {

            return;
        }


        // ==============================
        // DELETE BOOKING
        // ==============================

        String sql =
                "DELETE FROM reservations " +
                        "WHERE pnr = ?";


        try {

            Connection connection =
                    DBConnection.connect();


            if (connection == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Database connection failed!",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }


            PreparedStatement statement =
                    connection.prepareStatement(sql);


            statement.setInt(
                    1,
                    pnr
            );


            int rows =
                    statement.executeUpdate();


            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully!",
                        "Cancellation",
                        JOptionPane.INFORMATION_MESSAGE
                );


                clearFields();


                cancelButton.setEnabled(false);


                pnrField.setText("");


            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking could not be cancelled.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


            statement.close();

            connection.close();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // ==============================
    // CLEAR FIELDS
    // ==============================

    private void clearFields() {

        passengerField.setText("");

        trainNumberField.setText("");

        trainNameField.setText("");

        classField.setText("");

        dateField.setText("");

        sourceField.setText("");

        destinationField.setText("");

        cancelButton.setEnabled(false);
    }


    // ==============================
    // MAIN
    // ==============================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                Cancellation::new
        );
    }
}