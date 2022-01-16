package com.epam.mentorship.nosql.task1.repository;

import java.util.*;
import java.util.stream.Collectors;

public class Product {
    private String productName;
    private int productId;
    private int productSize;
    private double productPrice;

    public String getProductName() {
        return productName;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductSize() {
        return productSize;
    }

    public double getProductPrice() {
        return productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productId=" + productId +
                ", productSize=" + productSize +
                ", productPrice=" + productPrice +
                '}';
    }

    public Product() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter product name: ");
                this.productName = sc.nextLine();
            System.out.println("Enter product ID: ");
            while (sc.hasNextLine()) {
                this.productId = sc.nextInt();
            }
            System.out.println("Enter product size: ");
            while (sc.hasNextLine()) {
                this.productSize = sc.nextInt();
            }
            System.out.println("Enter product price: ");
            while (sc.hasNextLine()) {
                this.productPrice = sc.nextDouble();
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }
}

class ProductUnifier {
    List<Product> productList;
    int listSize;

    public ProductUnifier() {
        this.productList = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter number of products: ");
            this.listSize = scanner.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < listSize; i++) {
            this.productList.add(new Product());
        }
    }

    public void sortById() {
        this.productList = productList
                .stream()
                .sorted(Comparator.comparingInt(Product::getProductId))
                .collect(Collectors.toList());
    }

    public void display() {
        for (Product p : productList) {
            System.out.println(p);
        }
    }


    public static void main(String[] args) {
        ProductUnifier productUnifier = new ProductUnifier();
        productUnifier.display();
        productUnifier.sortById();
        productUnifier.display();

    }

}
