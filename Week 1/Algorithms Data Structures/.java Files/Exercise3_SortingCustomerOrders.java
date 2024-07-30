// Exercise 3: SORTING CUSTOMER ORDERS

import java.util.*;

// Order class 
class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer Name: " + customerName + ", Total Price: " + totalPrice;
    }
}

// Bubble Sort
class BubbleSort {
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
    }
}

// Quick Sort
class QuickSort {
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);  
            quickSort(orders, pi + 1, high); 
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}

public class Exercise3_SortingCustomerOrders {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("101", "Rohit", 1075.50),
            new Order("102", "Shreya", 763.00),
            new Order("103", "Sam", 612.00),
            new Order("104", "Emily", 1490.00),
            new Order("105", "Lily", 920.50)
        };

        // Before Sorting
        System.out.println("Before Sorting:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Bubble Sort
        BubbleSort.bubbleSort(orders);
        System.out.println("\nAfter Bubble Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }

        // Shuffling the array to perform Quick sort
        orders = new Order[]{
            new Order("101", "Rohit", 1075.50),
            new Order("102", "Shreya", 763.00),
            new Order("103", "Sam", 612.00),
            new Order("104", "Emily", 1490.00),
            new Order("105", "Lily", 920.50)
        };

        // Quick Sort
        QuickSort.quickSort(orders, 0, orders.length - 1);
        System.out.println("\nAfter Quick Sort:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}


// *********************OUTPUT*****************
// Before Sorting:
// Order ID: 101, Customer Name: Rohit, Total Price: 1075.5
// Order ID: 102, Customer Name: Shreya, Total Price: 763.0
// Order ID: 103, Customer Name: Sam, Total Price: 612.0
// Order ID: 104, Customer Name: Emily, Total Price: 1490.0
// Order ID: 105, Customer Name: Lily, Total Price: 920.5

// After Bubble Sort:
// Order ID: 103, Customer Name: Sam, Total Price: 612.0
// Order ID: 102, Customer Name: Shreya, Total Price: 763.0
// Order ID: 105, Customer Name: Lily, Total Price: 920.5
// Order ID: 101, Customer Name: Rohit, Total Price: 1075.5
// Order ID: 104, Customer Name: Emily, Total Price: 1490.0

// After Quick Sort:
// Order ID: 103, Customer Name: Sam, Total Price: 612.0
// Order ID: 102, Customer Name: Shreya, Total Price: 763.0
// Order ID: 105, Customer Name: Lily, Total Price: 920.5
// Order ID: 101, Customer Name: Rohit, Total Price: 1075.5
// Order ID: 104, Customer Name: Emily, Total Price: 1490.0
