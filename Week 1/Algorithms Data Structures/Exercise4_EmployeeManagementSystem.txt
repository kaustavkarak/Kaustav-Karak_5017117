// Exercise 4: EMPLOYEE MANAGEMENT SYSTEM

import java.util.Scanner;

// Employee class
class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Getters and Setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: " + salary;
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public void addEmployee(Employee employee) {
        if (size == employees.length) {
            System.out.println("Array is full, cannot add more employees.");
            return;
        }
        employees[size] = employee;
        size++;
    }

    // Search for an employee by ID
    public Employee searchEmployee(int employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                return employees[i];
            }
        }
        return null;
    }

    // Traverse all employees
    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // Delete an employee by ID
    public void deleteEmployee(int employeeId) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Employee not found.");
            return;
        }

        for (int i = index; i < size - 1; i++) {
            employees[i] = employees[i + 1];
        }

        employees[size - 1] = null;
        size--;
        System.out.println("Employee deleted successfully.");
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n---------------Employee Management System--------------------");
            System.out.println("Press: ");
            System.out.println("1: Add Employee");
            System.out.println("2: Search Employee");
            System.out.println("3: Traverse Employees");
            System.out.println("4: Delete Employee");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter Employee Salary: ");
                    double salary = scanner.nextDouble();
                    addEmployee(new Employee(id, name, position, salary));
                    break;
                case 2:
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    Employee emp = searchEmployee(searchId);
                    System.out.println("Search Result: " + (emp != null ? emp : "Employee not found"));
                    break;
                case 3:
                    System.out.println("All Employees:");
                    traverseEmployees();
                    break;
                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteEmployee(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting Employee Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose from the options.");
            }
        }
    }
}

public class Exercise4_EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);
        ems.displayMenu();
    }
}



// OUTPUT:
// ---------------Employee Management System--------------------
// Press: 
// 1: Add Employee
// 2: Search Employee
// 3: Traverse Employees
// 4: Delete Employee
// 5: Exit
// Enter your choice: 1
// Enter Employee ID: 101
// Enter Employee Name: Sam
// Enter Employee Position: CEO
// Enter Employee Salary: 7000000

// ---------------Employee Management System--------------------
// Press:
// 1: Add Employee
// 2: Search Employee
// 3: Traverse Employees
// 4: Delete Employee
// 5: Exit
// Enter your choice: 2
// Enter Employee ID to search: 101
// Search Result: Employee ID: 101, Name: Sam, Position: CEO, Salary: 7000000.0

// ---------------Employee Management System--------------------
// Press:
// 1: Add Employee
// 2: Search Employee
// 3: Traverse Employees
// 4: Delete Employee
// 5: Exit
// Enter your choice: 3
// All Employees:
// Employee ID: 101, Name: Sam, Position: CEO, Salary: 7000000.0

// ---------------Employee Management System--------------------
// Press:
// 1: Add Employee
// 2: Search Employee
// 3: Traverse Employees
// 4: Delete Employee
// 5: Exit
// Enter your choice: 4
// Enter Employee ID to delete: 101
// Employee deleted successfully.

// ---------------Employee Management System--------------------
// Press:
// 1: Add Employee
// 2: Search Employee
// 3: Traverse Employees
// 4: Delete Employee
// 5: Exit
// Enter your choice: 5
// Exiting Employee Management System