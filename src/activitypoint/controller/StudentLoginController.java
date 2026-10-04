package activitypoint.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import activitypoint.model.Application;
import activitypoint.model.Student;
import activitypoint.view.StudentLoginView;
import activitypoint.view.StudentView;

public class StudentLoginController {

    private StudentLoginView view;

    private ArrayList<Student> students;

    private ArrayList<Application> applications;

    public StudentLoginController(
            ArrayList<Student> students,
            ArrayList<Application> applications) {

        this.students = students;
        this.applications = applications;

        view = new StudentLoginView(this);
    }

    public void login(
            String username,
            String password) {

        File file =
                new File("student_accounts.txt");

        if (!file.exists()) {

            JOptionPane.showMessageDialog(
                    null,
                    "Student account file not found."
            );

            return;
        }

        boolean loginSuccessful = false;

        int loggedInRollNumber = -1;

        try {

            Scanner scanner =
                    new Scanner(file);

            while (scanner.hasNextLine()) {

                String line =
                        scanner.nextLine();

                String[] parts =
                        line.split("\\|");

                if (parts.length == 3) {

                    if (username.equals(parts[1])
                            && password.equals(parts[2])) {

                        loginSuccessful = true;

                        loggedInRollNumber =
                                Integer.parseInt(parts[0]);

                        break;
                    }
                }
            }

            scanner.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Unable to read student accounts."
            );

            return;
        }

        if (loginSuccessful) {

            JOptionPane.showMessageDialog(
                    null,
                    "Login successful!"
            );

            new StudentView(
                    students,
                    applications,
                    loggedInRollNumber
            );

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Invalid username or password."
            );
        }
    }
}