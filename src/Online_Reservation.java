import javax.swing.*;

public class Online_Reservation extends JFrame {

    public Online_Reservation() {

        setTitle("Online Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 100, 30);
        add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 60, 180, 30);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 110, 100, 30);
        add(passwordLabel);

        JPasswordField passwordField =
                new JPasswordField();

        passwordField.setBounds(150, 110, 180, 30);
        add(passwordField);

        JButton loginButton =
                new JButton("Login");

        loginButton.setBounds(150, 170, 100, 35);
        add(loginButton);


        loginButton.addActionListener(e -> {

            String username =
                    usernameField.getText();

            String password =
                    new String(
                            passwordField.getPassword()
                    );


            if (username.equals("admin")
                    && password.equals("1234")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login Successful!"
                );

                // Open menu
                openMenu();

                // Close login window
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Access Denied! Invalid username or password.",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        setVisible(true);
    }


    // =====================================
    // MAIN MENU
    // =====================================

    private void openMenu() {

        JFrame menu =
                new JFrame("Reservation System Menu");

        menu.setSize(400, 300);

        menu.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        menu.setLocationRelativeTo(null);

        menu.setLayout(null);


        JLabel title =
                new JLabel("Online Reservation System");

        title.setBounds(
                100,
                40,
                220,
                30
        );

        menu.add(title);


        // BOOK TICKET

        JButton bookButton =
                new JButton("Book Ticket");

        bookButton.setBounds(
                100,
                100,
                200,
                40
        );

        menu.add(bookButton);


        // CANCEL TICKET

        JButton cancelButton =
                new JButton("Cancel Ticket");

        cancelButton.setBounds(
                100,
                160,
                200,
                40
        );

        menu.add(cancelButton);


        // Open Reservation

        bookButton.addActionListener(e -> {

            new Reservation();

        });


        // Open Cancellation

        cancelButton.addActionListener(e -> {

            new Cancellation();

        });


        menu.setVisible(true);
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                Online_Reservation::new
        );
    }
}