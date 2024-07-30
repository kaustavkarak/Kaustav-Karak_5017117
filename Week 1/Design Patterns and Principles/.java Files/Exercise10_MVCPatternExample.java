// Exercise 10: Implementing the MVC PATTERN

// Student class
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

// StudentView class
class StudentView {
    // displayStudentDetails() Method
    public void displayStudentDetails(Student student) {
        System.out.println("Student Details:");
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getId());
        System.out.println("Grade: " + student.getGrade());
    }
}

// StudentController class that handles the communication between the model and the view
class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void setStudentName(String name) {
        model.setName(name);
    }

    public String getStudentName() {
        return model.getName();
    }

    public void setStudentId(String id) {
        model.setId(id);
    }

    public String getStudentId() {
        return model.getId();
    }

    public void setStudentGrade(String grade) {
        model.setGrade(grade);
    }

    public String getStudentGrade() {
        return model.getGrade();
    }

    public void updateView() {
        view.displayStudentDetails(model);
    }
}

// Main Class to demonstrate MVC Pattern
public class Exercise10_MVCPatternExample {
    public static void main(String[] args) {
        // Creating a Student model
        Student student = new Student("Sam Harnendex", "101", "A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();

        // Updating student details using StudentController
        controller.setStudentName("Sam Harnendex");
        controller.setStudentGrade("B");

        // Display updated details using StudentView
        controller.updateView();
    }
}



// OUTPUT:
// Student Details:
// Name: Sam Harnendex
// ID: 101
// Grade: A
// Student Details:
// Name: Sam Harnendex
// ID: 101
// Grade: B