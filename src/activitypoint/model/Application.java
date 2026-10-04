package activitypoint.model;

public class Application {

    private String name;
    private String rollNumber;
    private String date;
    private String certificatePath;
    private boolean approved;
    private int points;

    public Application(String name, String rollNumber, String date, String certificatePath) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.date = date;
        this.certificatePath = certificatePath;
        this.approved = false;
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getDate() {
        return date;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public boolean isApproved() {
        return approved;
    }

    public int getPoints() {
        return points;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}