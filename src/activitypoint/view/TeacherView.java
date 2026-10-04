
package activitypoint.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import activitypoint.FileManager;
import activitypoint.model.Application;
import activitypoint.model.Student;

public class TeacherView {

    private JFrame teacherFrame;

    public TeacherView(
            ArrayList<Student> students,
            ArrayList<Application> applications) {

        teacherFrame =
                new JFrame("Teacher - Activity Applications");

        JLabel heading =
                new JLabel("Teacher Applications");

        heading.setHorizontalAlignment(
                JLabel.CENTER
        );

        JPanel applicationsPanel =
                new JPanel();

        applicationsPanel.setLayout(
                new BoxLayout(
                        applicationsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        if (applications.isEmpty()) {

            JLabel noApplications =
                    new JLabel(
                            "No applications submitted yet."
                    );

            applicationsPanel.add(noApplications);

        } else {

            for (Application app : applications) {

                JPanel appPanel =
                        new JPanel();

                appPanel.setLayout(null);

                appPanel.setPreferredSize(
                        new Dimension(650, 180)
                );

                JLabel nameLabel =
                        new JLabel(
                                "Name: "
                                + app.getName()
                        );

                nameLabel.setBounds(
                        20, 10, 300, 25
                );

                JLabel rollLabel =
                        new JLabel(
                                "Roll Number: "
                                + app.getRollNumber()
                        );

                rollLabel.setBounds(
                        20, 40, 300, 25
                );

                JLabel dateLabel =
                        new JLabel(
                                "Date: "
                                + app.getDate()
                        );

                dateLabel.setBounds(
                        20, 70, 300, 25
                );

                JLabel statusLabel =
                        new JLabel(
                                "Status: "
                                + (
                                    app.isApproved()
                                    ? "Approved - "
                                      + app.getPoints()
                                      + " points"
                                    : "Pending"
                                )
                        );

                statusLabel.setBounds(
                        20, 100, 350, 25
                );

                JButton openButton =
                        new JButton(
                                "Open Certificate"
                        );

                openButton.setBounds(
                        350, 30, 160, 35
                );

                openButton.addActionListener(
                        openEvent -> {

                    try {

                        java.awt.Desktop
                                .getDesktop()
                                .open(
                                    new java.io.File(
                                        app.getCertificatePath()
                                    )
                                );

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                                teacherFrame,
                                "Unable to open certificate."
                        );
                    }
                });

                JButton approveButton =
                        new JButton("Approve");

                approveButton.setBounds(
                        350, 80, 160, 35
                );

                if (app.isApproved()) {
                    approveButton.setEnabled(false);
                }

                approveButton.addActionListener(
                        approveEvent -> {

                    if (app.isApproved()) {

                        JOptionPane.showMessageDialog(
                                teacherFrame,
                                "This application is already approved."
                        );

                        return;
                    }

                    String pointsText =
                            JOptionPane.showInputDialog(
                                    teacherFrame,
                                    "Enter activity points:"
                            );

                    if (pointsText != null) {

                        try {

                            int points =
                                    Integer.parseInt(
                                            pointsText
                                    );

                            if (points <= 0) {

                                JOptionPane.showMessageDialog(
                                        teacherFrame,
                                        "Points must be greater than 0."
                                );

                                return;
                            }

                            app.setPoints(points);

                            app.setApproved(true);

                            FileManager
                                    .saveApplications(
                                            applications
                                    );

                            for (Student student
                                    : students) {

                                if (
                                    app.getRollNumber()
                                    .equals(
                                        String.valueOf(
                                            student.getRollNumber()
                                        )
                                )) {

                                    student.addActivityPoints(
                                            points
                                    );

                                    FileManager
                                            .saveStudents(
                                                    students
                                            );

                                    break;
                                }
                            }

                            statusLabel.setText(
                                    "Status: Approved - "
                                    + points
                                    + " points"
                            );

                            approveButton.setEnabled(
                                    false
                            );

                            JOptionPane.showMessageDialog(
                                    teacherFrame,
                                    "Application approved successfully!"
                            );

                        } catch (
                                NumberFormatException ex) {

                            JOptionPane.showMessageDialog(
                                    teacherFrame,
                                    "Please enter a valid number."
                            );
                        }
                    }
                });

                appPanel.add(nameLabel);
                appPanel.add(rollLabel);
                appPanel.add(dateLabel);
                appPanel.add(statusLabel);
                appPanel.add(openButton);
                appPanel.add(approveButton);

                applicationsPanel.add(appPanel);
            }
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        applicationsPanel
                );

        teacherFrame.setLayout(
                new BorderLayout()
        );

        teacherFrame.add(
                heading,
                BorderLayout.NORTH
        );

        teacherFrame.add(
                scrollPane,
                BorderLayout.CENTER
        );

        teacherFrame.setSize(
                700,
                450
        );

        teacherFrame.setLocationRelativeTo(null);

        teacherFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        teacherFrame.setVisible(true);
    }
}