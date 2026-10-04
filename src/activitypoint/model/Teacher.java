package activitypoint.model;

public class Teacher extends User {

    public Teacher(String name) {
        super(name);
    }

    @Override
    public String displayRole() {
        return "Teacher";
    }
}