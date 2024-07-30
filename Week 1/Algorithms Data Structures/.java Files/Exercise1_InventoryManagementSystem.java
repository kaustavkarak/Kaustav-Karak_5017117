// Exercise 1: INVENTORY MANAGEMENT SYSTEM

import java.util.HashMap;
import java.util.Scanner;

// Product Class
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Quantity: " + quantity + ", Price: " + price;
    }
}

public class Exercise1_InventoryManagementSystem {
    // HashMap to store products
    private HashMap<String, Product> inventory = new HashMap<>();

    // Add Product
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("Product already exists.");
        } else {
            inventory.put(product.getProductId(), product);
            System.out.println("Product added successfully.");
        }
    }

    // Update Product
    public void updateProduct(String productId, int quantity, double price) {
        Product product = inventory.get(productId);
        if (product != null) {
            product.setQuantity(quantity);
            product.setPrice(price);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete Product
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Display Products
    public void displayProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        Exercise1_InventoryManagementSystem ims = new Exercise1_InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n---------Inventory Management System---------");
            System.out.println("Press:");
            System.out.println("1: Add Product");
            System.out.println("2: Update Product");
            System.out.println("3: Delete Product");
            System.out.println("4: Display Products");
            System.out.println("5: Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String productId = scanner.nextLine();
                    System.out.print("Enter Product Name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter Product Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    ims.addProduct(new Product(productId, productName, quantity, price));
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    productId = scanner.nextLine();
                    System.out.print("Enter New Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter New Price: ");
                    price = scanner.nextDouble();
                    ims.updateProduct(productId, quantity, price);
                    break;
                case 3:
                    System.out.print("Enter Product ID: ");
                    productId = scanner.nextLine();
                    ims.deleteProduct(productId);
                    break;
                case 4:
                    ims.displayProducts();
                    break;
                case 5:
                    System.out.println("Exiting Inventory Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please choose from the options.");
            }
        }
    }
}



// OUTPUT:
// ---------Inventory Management System---------
// Press:
// 1: Add Product
// 2: Update Product
// 3: Delete Product
// 4: Display Products
// 5: Exit
// Enter your choice: 1
// Enter Product ID: 101
// Enter Product Name: Laptop
// Enter Product Quantity: 56
// Enter Product Price: 64999
// Product added successfully.

// ---------Inventory Management System---------
// Press:
// 1: Add Product
// 2: Update Product
// 3: Delete Product
// 4: Display Products
// 5: Exit
// Enter your choice: 2
// Enter Product ID: 101
// Enter New Quantity: 37
// Enter New Price: 71999
// Product updated successfully.

// ---------Inventory Management System---------
// Press:
// 1: Add Product
// 2: Update Product
// 3: Delete Product
// 4: Display Products
// 5: Exit
// Enter your choice: 3
// Enter Product ID: 101
// Product deleted successfully.

// ---------Inventory Management System---------
// Press:
// 1: Add Product
// 2: Update Product
// 3: Delete Product
// 4: Display Products
// 5: Exit
// Enter your choice: 5
// Exiting Inventory Management System.

