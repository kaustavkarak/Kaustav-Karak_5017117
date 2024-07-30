// Exercise 2: E-COMMERCE PLATFORM SEARCH

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Product Class 
class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + productName + ", Category: " + category;
    }
}

// Linear Search
class LinearSearch {
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }
}

// Binary Search
class BinarySearch {
    public static Product binarySearch(Product[] products, String targetName) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = products[mid].getProductName().compareToIgnoreCase(targetName);

            if (cmp == 0) {
                return products[mid];
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }
}

public class Exercise2_ECommercePlatformSearch {
    public static void main(String[] args) {
        Product[] products = {
            new Product("101", "Headphones", "Electronics"),
            new Product("102", "Notebook", "Stationery"),
            new Product("103", "Refrigerator", "Home Appliances"),
            new Product("104", "Bed", "Furniture"),
            new Product("115", "Air Conditioner", "Home Appliances")
        };

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n-----------E-Commerce Platform Search-------------");
            System.out.println("Press: ");
            System.out.println("1: Linear Search by Product Name");
            System.out.println("2: Binary Search by Product Name");
            System.out.println("3: Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            if (choice == 3) {
                System.out.println("Exiting E-Commerce Platform Search.");
                break;
            }

            System.out.print("Enter the name of the product: ");
            String productName = scanner.nextLine();

            Product result = null;
            switch (choice) {
                case 1:
                    result = LinearSearch.linearSearch(products, productName);
                    break;
                case 2:
                    result = BinarySearch.binarySearch(products, productName);
                    break;
                default:
                    System.out.println("Invalid choice. Please choose from options.");
                    continue;
            }

            if (result != null) {
                System.out.println("Product found: " + result);
            } else {
                System.out.println("Product not found.");
            }
        }
        scanner.close();
    }
}



// OUTPUT:
// -----------E-Commerce Platform Search-------------
// Press: 
// 1: Linear Search by Product Name
// 2: Binary Search by Product Name
// 3: Exit
// Enter your choice: 1
// Enter the name of the product: Headphones
// Product found: Product ID: 101, Name: Headphones, Category: Electronics

// -----------E-Commerce Platform Search-------------
// Press:
// 1: Linear Search by Product Name
// 2: Binary Search by Product Name
// 3: Exit
// Enter your choice: 2
// Enter the name of the product: Notebook
// Product found: Product ID: 102, Name: Notebook, Category: Stationery

// -----------E-Commerce Platform Search-------------
// Press:
// 1: Linear Search by Product Name
// 2: Binary Search by Product Name
// 3: Exit
// Enter your choice: 3
// Exiting E-Commerce Platform Search