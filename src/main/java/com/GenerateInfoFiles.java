package com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is responsible for generating test files for the sales management system.
 * It creates three types of files:
 * 1. Sales files for each salesman
 * 2. A products information file
 * 3. A salesmen information file
 */
public class GenerateInfoFiles {

    // Constants for file names
    private static final String PRODUCTS_FILE_NAME = "products.txt";
    private static final String SALESMEN_INFO_FILE_NAME = "salesmen_info.txt";
    private static final String SALESMAN_FILE_PREFIX = "salesman_";

    // Lists for random data generation
    private static final String[] FIRST_NAMES = {
        "John", "Maria", "Carlos", "Ana", "David", "Sofia", "Michael", "Laura", 
        "James", "Patricia", "Robert", "Jennifer", "William", "Linda", "Richard", "Elizabeth"
    };

    private static final String[] LAST_NAMES = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson", "Thomas"
    };

    private static final String[] PRODUCT_NAMES = {
        "Laptop", "Smartphone", "Tablet", "Monitor", "Keyboard", "Mouse", "Headphones", "Printer",
        "Scanner", "Camera", "TV", "Speaker", "Microphone", "Router", "Hard Drive", "USB Drive",
        "Graphics Card", "Processor", "RAM", "Motherboard", "Power Supply", "Case", "Cooling Fan"
    };

    private static final String[] DOCUMENT_TYPES = {"CC", "CT"};

    private static final Random random = new Random();

    /**
     * Main method to execute the file generation process.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Generate 10 products
            List<Product> products = createProductsFile(10);

            // Generate 5 salesmen
            List<Salesman> salesmen = createSalesManInfoFile(5);

            // Generate sales files for each salesman
            for (Salesman salesman : salesmen) {
                createSalesMenFile(5, salesman.getFirstName() + " " + salesman.getLastName(), 
                                  salesman.getDocumentNumber(), products);
            }

            System.out.println("File generation completed successfully.");
        } catch (IOException e) {
            System.err.println("Error generating files: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates a file with sales information for a specific salesman.
     * 
     * @param randomSalesCount Number of random sales to generate
     * @param name Name of the salesman
     * @param id Document number of the salesman
     * @param products List of available products
     * @return The file created
     * @throws IOException If there's an error writing to the file
     */
    public static File createSalesMenFile(int randomSalesCount, String name, long id, List<Product> products) throws IOException {
        String documentType = DOCUMENT_TYPES[random.nextInt(DOCUMENT_TYPES.length)];
        String fileName = SALESMAN_FILE_PREFIX + id + ".txt";
        File file = new File(fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            // Write salesman document information
            writer.write(documentType + ";" + id);
            writer.newLine();

            // Generate random sales
            for (int i = 0; i < randomSalesCount; i++) {
                Product product = products.get(random.nextInt(products.size()));
                int quantity = random.nextInt(10) + 1; // Random quantity between 1 and 10

                writer.write(product.getId() + ";" + quantity + ";");
                writer.newLine();
            }
        }

        return file;
    }

    /**
     * Creates a file with information about products.
     * 
     * @param productsCount Number of products to generate
     * @return List of generated products
     * @throws IOException If there's an error writing to the file
     */
    public static List<Product> createProductsFile(int productsCount) throws IOException {
        List<Product> products = new ArrayList<>();
        File file = new File(PRODUCTS_FILE_NAME);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < productsCount; i++) {
                String productId = "P" + (i + 1);
                String productName = PRODUCT_NAMES[i % PRODUCT_NAMES.length];
                double price = 10.0 + (random.nextDouble() * 990.0); // Random price between 10 and 1000
                price = Math.round(price * 100.0) / 100.0; // Round to 2 decimal places

                Product product = new Product(productId, productName, price);
                products.add(product);

                writer.write(productId + ";" + productName + ";" + price);
                writer.newLine();
            }
        }

        return products;
    }

    /**
     * Creates a file with information about salesmen.
     * 
     * @param salesmanCount Number of salesmen to generate
     * @return List of generated salesmen
     * @throws IOException If there's an error writing to the file
     */
    public static List<Salesman> createSalesManInfoFile(int salesmanCount) throws IOException {
        List<Salesman> salesmen = new ArrayList<>();
        File file = new File(SALESMEN_INFO_FILE_NAME);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < salesmanCount; i++) {
                String documentType = DOCUMENT_TYPES[random.nextInt(DOCUMENT_TYPES.length)];
                long documentNumber = 1000000000L + random.nextInt(900000000);
                String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
                String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

                Salesman salesman = new Salesman(documentType, documentNumber, firstName, lastName);
                salesmen.add(salesman);

                writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName);
                writer.newLine();
            }
        }

        return salesmen;
    }

    /**
     * Inner class representing a Product.
     */
    private static class Product {
        private final String id;
        private final String name;
        private final double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    /**
     * Inner class representing a Salesman.
     */
    private static class Salesman {
        private final String documentType;
        private final long documentNumber;
        private final String firstName;
        private final String lastName;

        public Salesman(String documentType, long documentNumber, String firstName, String lastName) {
            this.documentType = documentType;
            this.documentNumber = documentNumber;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getDocumentType() {
            return documentType;
        }

        public long getDocumentNumber() {
            return documentNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}
