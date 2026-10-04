package activitypoint.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import activitypoint.controller.StudentLoginController;
public class StudentLoginView {

    private JFrame frame;
    private StudentLoginController controller;

    public StudentLoginView(StudentLoginController controller) {

        this.controller = controller;

        frame = new JFrame("Student Login");

        JLabel heading = new JLabel("Student Login");
        heading.setHorizontalAlignment(JLabel.CENTER);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {

        	String username = usernameField.getText();
        	String password = new String(passwordField.getPassword());

        	controller.login(username, password);
            

        });

        frame.setLayout(null);

        heading.setBounds(150, 30, 200, 40);

        usernameLabel.setBounds(80, 90, 100, 30);
        usernameField.setBounds(190, 90, 220, 30);

        passwordLabel.setBounds(80, 140, 100, 30);
        passwordField.setBounds(190, 140, 220, 30);

        loginButton.setBounds(190, 200, 100, 35);

        frame.add(heading);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
    }
}