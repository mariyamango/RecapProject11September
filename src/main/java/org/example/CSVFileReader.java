package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import static org.example.Main.*;

public class CSVFileReader {
    public static void processCSVFile(ProductRepo productRepo, String filePath) {
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int productId = Integer.parseInt(values[0]);
                String productName = values[1];
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(values[3]));

                Product product = new Product(productId, productName, price,"");
                productRepo.addProduct(product);
            }
            System.out.println(ANSI_GREEN_BOLD + "Products loaded from CSV successfully." + ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ANSI_RED_BOLD + "Error reading the CSV file: " + e.getMessage() + ANSI_RESET);
        }
    }
}
