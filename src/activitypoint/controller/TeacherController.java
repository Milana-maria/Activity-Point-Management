
package activitypoint.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import activitypoint.model.Application;
import activitypoint.model.Student;
import activitypoint.view.TeacherView;

public class TeacherController {

    private ArrayList<Student> students;
    private ArrayList<Application> applications;

    private final String teacherPassword = "teacher123";

    public TeacherController(
            ArrayList<Student> students,
            ArrayList<Application> applications) {

        this.students = students;
        this.applications = applications;

        login();
    }

    private void login() {

        String password =
                JOptionPane.showInputDialog(
                        null,
                        "Enter Teacher Password:"
                );

        if (password == null) {
            return;
        }

        if (password.equals(teacherPassword)) {

            new TeacherView(
                    students,
                    applications
            );

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Incorrect password!"
            );
        }
    }
}
