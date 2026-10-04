package activitypoint;

import activitypoint.model.Application;
import activitypoint.model.Student;
import activitypoint.model.Teacher;
import activitypoint.model.User;
import activitypoint.controller.StudentLoginController;
import javax.swing.*;
import activitypoint.controller.TeacherController;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class Main {

    public static void main(String[] args) {
    	ArrayList<Application> applications = new ArrayList<>();
    	User teacher = new Teacher("Activity Point Teacher");
    	FileManager.loadApplications(applications);
    	Student s1 = new Student(1, "Student 1", 0);
    	Student s2 = new Student(2, "Student 2", 0);
    	Student s3 = new Student(3, "Student 3", 0);
    	ArrayList<Student> students = new ArrayList<>();

    	students.add(new Student(1, "ABHINAV SHINOJ", 0));
    	students.add(new Student(2, "ABHISHEK P NAIR", 0));
    	students.add(new Student(3, "ABIYA PRAKASH A B", 0));
    	students.add(new Student(4, "ADWAITH ANIL", 0));
    	students.add(new Student(5, "ADWAITH D NAIR", 0));
    	students.add(new Student(6, "AFIA S", 0));
    	students.add(new Student(7, "ALAN BAIJU", 0));
    	students.add(new Student(8, "ALBIN ANTONY", 0));
    	students.add(new Student(9, "ALBIN BINU", 0));
    	students.add(new Student(10, "ALONA CINTO", 0));
    	students.add(new Student(11, "AMITHA P A", 0));
    	students.add(new Student(12, "ANNS MARIA GINS", 0));
    	students.add(new Student(13, "ARCHANA SUDEEP", 0));
    	students.add(new Student(14, "ASHIN JOSEPH SOL", 0));
    	students.add(new Student(15, "ASWIN BIJU", 0));
    	students.add(new Student(16, "ATHUL R NAIR", 0));
    	students.add(new Student(17, "AUGUSTINE JOMON", 0));
    	students.add(new Student(18, "CHRIS JOSEPH SHINE", 0));
    	students.add(new Student(19, "CYRIAC JOSE", 0));
    	students.add(new Student(20, "DIYA GOPAKUMAR", 0));
    	students.add(new Student(21, "DONA VINCENT", 0));
    	students.add(new Student(22, "EFINOVA SABU", 0));
    	students.add(new Student(23, "ELAINE ROSE SUNIL", 0));
    	students.add(new Student(24, "ELSA ROSE JIMMY", 0));
    	students.add(new Student(25, "EMMANUEL THOMAS ARUN", 0));
    	students.add(new Student(26, "EVANGELY MARIAM SHANTIS", 0));
    	students.add(new Student(27, "GANGA P G", 0));
    	students.add(new Student(28, "GAUTHAM DAS", 0));
    	students.add(new Student(29, "GEO MATHEW SAJU", 0));
    	students.add(new Student(30, "GEORGE THOMAS", 0));
    	students.add(new Student(31, "GOUTHAM KRISHNA V V", 0));
    	students.add(new Student(32, "HARJAYANTH S", 0));
    	students.add(new Student(33, "HARITHA ROY", 0));
    	students.add(new Student(34, "IRIN GEORGE", 0));
    	students.add(new Student(35, "JAIDIN MEJE", 0));
    	students.add(new Student(36, "JEES REJI", 0));
    	students.add(new Student(37, "JEREMY JOI", 0));
    	students.add(new Student(38, "JEROME ABRAHAM PIOUS", 0));
    	students.add(new Student(39, "JESTO JOHNSON", 0));
    	students.add(new Student(40, "JESVIN JOIES", 0));
    	students.add(new Student(41, "JEWEL MARIYA VARGHESE", 0));
    	students.add(new Student(42, "JOEL JOSE", 0));
    	students.add(new Student(43, "JOEL JOSEPH", 0));
    	students.add(new Student(44, "JOHAN P MANOJ", 0));
    	students.add(new Student(45, "JOICE BENNY", 0));
    	students.add(new Student(46, "JOSEPH MICHAEL", 0));
    	students.add(new Student(47, "JOSU JAISON", 0));
    	students.add(new Student(48, "KARTHIK S GOPAL", 0));
    	students.add(new Student(49, "KRISHNAJITH A", 0));
    	students.add(new Student(50, "LEKSHMI SREEKUMAR", 0));
    	students.add(new Student(51, "LIYA TOMY", 0));
    	students.add(new Student(52, "MADHAV SURESH", 0));
    	students.add(new Student(53, "MICHAEL JAMES", 0));
    	students.add(new Student(54, "MILANA MARIA MATHEW", 0));
    	students.add(new Student(55, "MINNA AUGUSTIN", 0));
    	students.add(new Student(56, "MIRON VINCENT", 0));
    	students.add(new Student(57, "MRUDUL R NAIR", 0));
    	students.add(new Student(58, "NIRANJAN MANOJ", 0));
    	students.add(new Student(59, "NISSIMOL SABU", 0));
    	students.add(new Student(60, "PRIYADARSHINI M R", 0));
    	students.add(new Student(61, "RHEA THOMAS", 0));
    	students.add(new Student(62, "RONY SIBY", 0));
    	students.add(new Student(63, "SALMAN S", 0));
    	students.add(new Student(64, "SIVAPRIYA M", 0));
    	students.add(new Student(65, "SREEHARI S", 0));
    	students.add(new Student(66, "THEJUS TOM PIOUS", 0));
    	students.add(new Student(67, "TISSA ROSE MATHEW", 0));
    	students.add(new Student(68, "VYGA SAJIKUMAR", 0));
    	FileManager.loadStudents(students);
    	System.out.println(s1.getName() + " - " + s1.getActivityPoints());
    	System.out.println(s2.getName() + " - " + s2.getActivityPoints());
    	System.out.println(s3.getName() + " - " + s3.getActivityPoints());
        JFrame frame = new JFrame("Activity Point Management System");

        JLabel title = new JLabel("Activity Point Management System");
        title.setBounds(100, 50, 300, 30);

        JButton studentButton = new JButton("Student");
        studentButton.setPreferredSize(new Dimension(120, 40));

        studentButton.addActionListener(e -> {

        	new StudentLoginController(
        	        students,
        	        applications
        	);

        });
        JButton teacherButton = new JButton("Teacher");
        teacherButton.setPreferredSize(new Dimension(120, 40));
        teacherButton.addActionListener(e -> {

            new TeacherController(
                    students,
                    applications
            );

        });

          

            	        
        frame.setLayout(new BorderLayout());

        title.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 28));

        frame.add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new java.awt.GridBagLayout());

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(20, 20, 20, 20);

        studentButton.setPreferredSize(new Dimension(180, 70));
        teacherButton.setPreferredSize(new Dimension(180, 70));

        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(studentButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(teacherButton, gbc);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setSize(700, 450);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}