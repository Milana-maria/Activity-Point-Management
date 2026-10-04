package activitypoint.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import activitypoint.FileManager;
import activitypoint.model.Application;
import activitypoint.model.Student;

public class StudentView {

    private JFrame frame;

    public StudentView(
            ArrayList<Student> students,
            ArrayList<Application> applications,
            int loggedInRollNumber) {

        frame = new JFrame("Student Activity Points");

        Student foundStudent = null;

        for (Student student : students) {
            if (student.getRollNumber() == loggedInRollNumber) {
                foundStudent = student;
                break;
            }
        }

        if (foundStudent == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Student not found."
            );
            return;
        }

        // Make a final reference for use inside button actions
        final Student loggedInStudent = foundStudent;

        JLabel heading = new JLabel(
                "Welcome, " + loggedInStudent.getName()
        );

        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        26
                )
        );

        String[] columns = {
                "Roll No.",
                "Name",
                "Activity Points"
        };

        Object[][] data = new Object[students.size()][3];

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            data[i][0] = student.getRollNumber();
            data[i][1] = student.getName();
            data[i][2] = student.getActivityPoints();
        }

        JTable studentTable =
                new JTable(data, columns);

        JScrollPane scrollPane =
                new JScrollPane(studentTable);

        JButton applyButton =
                new JButton("Apply for Activity");

        applyButton.setPreferredSize(
                new Dimension(200, 45)
        );

        applyButton.addActionListener(e -> {

            openApplicationForm(
                    loggedInStudent,
                    applications
            );

        });

        JLabel pointsLabel = new JLabel(
                "Your Activity Points: "
                + loggedInStudent.getActivityPoints()
        );

        pointsLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        pointsLabel.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        18
                )
        );

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.add(
                pointsLabel,
                BorderLayout.NORTH
        );

        bottomPanel.add(
                applyButton,
                BorderLayout.SOUTH
        );

        frame.setLayout(
                new BorderLayout()
        );

        frame.add(
                heading,
                BorderLayout.NORTH
        );

        frame.add(
                scrollPane,
                BorderLayout.CENTER
        );

        frame.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }

    private void openApplicationForm(
            Student student,
            ArrayList<Application> applications) {

        JFrame applyFrame =
                new JFrame("Activity Application");

        JLabel heading =
                new JLabel("Activity Application");

        heading.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        heading.setFont(
                new java.awt.Font(
                        "Arial",
                        java.awt.Font.BOLD,
                        26
                )
        );

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(12, 12, 12, 12);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel nameLabel =
                new JLabel("Student Name:");

        JTextField nameField =
                new JTextField(student.getName());

        nameField.setEditable(false);

        JLabel rollLabel =
                new JLabel("Roll Number:");

        JTextField rollField =
                new JTextField(
                        String.valueOf(
                                student.getRollNumber()
                        )
                );

        rollField.setEditable(false);

        JLabel dateLabel =
                new JLabel("Activity Date:");

        JTextField dateField =
                new JTextField();

        dateField.setEditable(false);

        JButton dateButton =
                new JButton("Select Date");

        dateButton.addActionListener(e -> {

            JDialog dateDialog =
                    new JDialog(
                            applyFrame,
                            "Select Activity Date",
                            true
                    );

            dateDialog.setLayout(
                    new java.awt.GridLayout(
                            4, 2, 10, 10
                    )
            );

            Integer[] days = new Integer[31];

            for (int i = 0; i < 31; i++) {
                days[i] = i + 1;
            }

            String[] months = {
                    "01", "02", "03", "04",
                    "05", "06", "07", "08",
                    "09", "10", "11", "12"
            };

            Integer[] years = new Integer[11];

            int currentYear =
                    LocalDate.now().getYear();

            for (int i = 0; i < 11; i++) {
                years[i] =
                        currentYear - 5 + i;
            }

            JComboBox<Integer> dayBox =
                    new JComboBox<>(days);

            JComboBox<String> monthBox =
                    new JComboBox<>(months);

            JComboBox<Integer> yearBox =
                    new JComboBox<>(years);

            JButton selectButton =
                    new JButton("Select");

            dateDialog.add(
                    new JLabel("Day:")
            );

            dateDialog.add(dayBox);

            dateDialog.add(
                    new JLabel("Month:")
            );

            dateDialog.add(monthBox);

            dateDialog.add(
                    new JLabel("Year:")
            );

            dateDialog.add(yearBox);

            dateDialog.add(new JLabel());

            dateDialog.add(selectButton);

            selectButton.addActionListener(
                    selectEvent -> {

                try {

                    int day =
                            (Integer)
                            dayBox.getSelectedItem();

                    int month =
                            Integer.parseInt(
                                    (String)
                                    monthBox
                                            .getSelectedItem()
                            );

                    int year =
                            (Integer)
                            yearBox.getSelectedItem();

                    LocalDate selectedDate =
                            LocalDate.of(
                                    year,
                                    month,
                                    day
                            );

                    dateField.setText(
                            String.format(
                                    "%02d-%02d-%04d",
                                    selectedDate
                                            .getDayOfMonth(),
                                    selectedDate
                                            .getMonthValue(),
                                    selectedDate
                                            .getYear()
                            )
                    );

                    dateDialog.dispose();

                } catch (DateTimeException ex) {

                    JOptionPane.showMessageDialog(
                            dateDialog,
                            "Invalid date."
                    );
                }
            });

            dateDialog.setSize(
                    350,
                    220
            );

            dateDialog.setLocationRelativeTo(
                    applyFrame
            );

            dateDialog.setVisible(true);
        });

        JButton uploadButton =
                new JButton("Upload Certificate");

        JLabel certificateLabel =
                new JLabel(
                        "No certificate selected"
                );

        final String[] certificatePath = {""};

        uploadButton.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            int result =
                    chooser.showOpenDialog(
                            applyFrame
                    );

            if (result ==
                    JFileChooser.APPROVE_OPTION) {

                File selectedFile =
                        chooser.getSelectedFile();

                File folder =
                        new File("certificates");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String newFileName =
                        System.currentTimeMillis()
                        + "_"
                        + selectedFile.getName();

                File destination =
                        new File(
                                folder,
                                newFileName
                        );

                try {

                    Files.copy(
                            selectedFile.toPath(),
                            destination.toPath(),
                            StandardCopyOption
                                    .REPLACE_EXISTING
                    );

                    certificatePath[0] =
                            destination.getPath();

                    certificateLabel.setText(
                            selectedFile.getName()
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            applyFrame,
                            "Unable to upload certificate."
                    );
                }
            }
        });

        JButton submitButton =
                new JButton("Submit");

        submitButton.addActionListener(e -> {

            if (dateField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        applyFrame,
                        "Please select the activity date."
                );

                return;
            }

            if (certificatePath[0].isEmpty()) {

                JOptionPane.showMessageDialog(
                        applyFrame,
                        "Please upload a certificate."
                );

                return;
            }

            Application application =
                    new Application(
                            student.getName(),
                            String.valueOf(
                                    student.getRollNumber()
                            ),
                            dateField.getText(),
                            certificatePath[0]
                    );

            applications.add(application);

            FileManager.saveApplications(
                    applications
            );

            JOptionPane.showMessageDialog(
                    applyFrame,
                    "Application submitted successfully!"
            );

            applyFrame.dispose();
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(rollLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(rollField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        gbc.gridx = 2;
        formPanel.add(dateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(uploadButton, gbc);

        gbc.gridy = 4;
        formPanel.add(certificateLabel, gbc);

        gbc.gridy = 5;
        formPanel.add(submitButton, gbc);

        applyFrame.setLayout(
                new BorderLayout()
        );

        applyFrame.add(
                heading,
                BorderLayout.NORTH
        );

        applyFrame.add(
                formPanel,
                BorderLayout.CENTER
        );

        applyFrame.setSize(
                800,
                550
        );

        applyFrame.setLocationRelativeTo(
                frame
        );

        applyFrame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        applyFrame.setVisible(true);
    }
}