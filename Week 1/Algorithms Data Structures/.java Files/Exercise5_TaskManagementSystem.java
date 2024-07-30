// Exercise 5: TASK MANAGEMENT SYSTEM

import java.util.Scanner;

// Task class 
class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    // Getters and Setters
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Name: " + taskName + ", Status: " + status;
    }
}

// Singly Linke List
class SinglyLinkedList {
    private class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Add a task
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Search for a task by ID
    public Task searchTask(int taskId) {
        Node temp = head;
        while (temp != null) {
            if (temp.task.getTaskId() == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null;
    }

    // Traverse all tasks
    public void traverseTasks() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    // Delete a task by ID
    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.task.getTaskId() != taskId) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Task not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Task deleted successfully.");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-------------Task Management System----------------");
            System.out.println("Press:");
            System.out.println("1: Add Task");
            System.out.println("2: Search Task");
            System.out.println("3: Traverse Tasks");
            System.out.println("4: Delete Task");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Task Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Task Status: ");
                    String status = scanner.nextLine();
                    addTask(new Task(id, name, status));
                    break;
                case 2:
                    System.out.print("Enter Task ID to search: ");
                    int searchId = scanner.nextInt();
                    Task task = searchTask(searchId);
                    System.out.println("Search Result: " + (task != null ? task : "Task not found"));
                    break;
                case 3:
                    System.out.println("All Tasks:");
                    traverseTasks();
                    break;
                case 4:
                    System.out.print("Enter Task ID to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteTask(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting Task Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose from the options.");
            }
        }
    }
}

public class Exercise5_TaskManagementSystem {
    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();
        taskList.displayMenu();
    }
}


// OUTPUT:
// -------------Task Management System----------------
// Press:
// 1: Add Task
// 2: Search Task
// 3: Traverse Tasks
// 4: Delete Task
// 5: Exit
// Enter your choice: 1
// Enter Task ID: 1 
// Enter Task Name: Read Book
// Enter Task Status: Ongoing

// -------------Task Management System----------------
// Press:
// 1: Add Task
// 2: Search Task
// 3: Traverse Tasks
// 4: Delete Task
// 5: Exit
// Enter your choice: 2
// Enter Task ID to search: 1
// Search Result: Task ID: 1, Name: Read Book, Status: Ongoing

// -------------Task Management System----------------
// Press:
// 1: Add Task
// 2: Search Task
// 3: Traverse Tasks
// 4: Delete Task
// 5: Exit
// Enter your choice: 3
// All Tasks:
// Task ID: 1, Name: Read Book, Status: Ongoing

// -------------Task Management System----------------
// Press:
// 1: Add Task
// 2: Search Task
// 3: Traverse Tasks
// 4: Delete Task
// 5: Exit
// Enter your choice: 4
// Enter Task ID to delete: 1
// Task deleted successfully.

// -------------Task Management System----------------
// Press:
// 1: Add Task
// 2: Search Task
// 3: Traverse Tasks
// 4: Delete Task
// 5: Exit
// Enter your choice: 5
// Exiting Task Management System.