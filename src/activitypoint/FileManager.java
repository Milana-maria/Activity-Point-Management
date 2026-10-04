package activitypoint;
import activitypoint.model.Application;
import activitypoint.model.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class FileManager {

    // Save student details and activity points
    public static void saveStudents(ArrayList<Student> students) {

        File file = new File("students.txt");

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            for (Student student : students) {

                writer.println(
                    student.getRollNumber() + "|" +
                    student.getName() + "|" +
                    student.getActivityPoints()
                );
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Load student activity points
    public static void loadStudents(ArrayList<Student> students) {

        File file = new File("students.txt");

        if (!file.exists()) {
            return;
        }

        try {
            java.util.Scanner scanner = new java.util.Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                int rollNumber = Integer.parseInt(parts[0]);
                int points = Integer.parseInt(parts[2]);

                for (Student student : students) {

                    if (student.getRollNumber() == rollNumber) {

                        student.setActivityPoints(points);
                        break;
                    }
                }
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Save applications
    public static void saveApplications(ArrayList<Application> applications) {

        File file = new File("applications.txt");

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(file));

            for (Application app : applications) {

            	writer.println(
            		    app.getName() + "|" +
            		    app.getRollNumber() + "|" +
            		    app.getDate() + "|" +
            		    app.getCertificatePath() + "|" +
            		    app.isApproved() + "|" +
            		    app.getPoints()
            		);
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Load applications
    public static void loadApplications(ArrayList<Application> applications) {

        File file = new File("applications.txt");

        if (!file.exists()) {
            return;
        }

        try {
            java.util.Scanner scanner = new java.util.Scanner(file);

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split("\\|", -1);

                String name = parts[0];
                String rollNumber = parts[1];
                String date = parts[2];
                String certificatePath = parts[3];

                Application app = new Application(
                    name,
                    rollNumber,
                    date,
                    certificatePath
                );

                boolean approved = Boolean.parseBoolean(parts[4]);
                int points = Integer.parseInt(parts[5]);

                if (approved) {
                    app.setPoints(points);
                    app.setApproved(true);
                }

                applications.add(app);
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}