package activitypoint.model;

public class Student extends User {
	
    private int rollNumber;
    
    private int activityPoints;

   
    public Student(int rollNumber, String name, int activityPoints) {
        super(name);
        this.rollNumber = rollNumber;
        this.activityPoints = activityPoints;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }

    public int getActivityPoints() {
        return activityPoints;
    }

    public void addActivityPoints(int points) {
        activityPoints = activityPoints + points;
    }
    public void setActivityPoints(int activityPoints) {
        this.activityPoints = activityPoints;
    }
    @Override
    public String displayRole() {
        return "Student";
    }
}
