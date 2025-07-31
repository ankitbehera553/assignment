import java.io.FileReader;
import java.sql.*;
import com.opencsv.CSVReader;

public class LoadProductsCSV {

    // Change this based on your DB type and credentials
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/assignment";
    static final String JDBC_USER = "root";
    static final String JDBC_PASSWORD = "Ankit@553";

    public static void main(String[] args) {
        String csvFile = "C:\\Users\\ACER\\Desktop\\ecommerce-dataset-main\\archive\\archive\\products.csv";

        try (
            Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            CSVReader reader = new CSVReader(new FileReader(csvFile))
        ) {
            String insertQuery = "INSERT INTO products " +
                    "(id, cost, category, name, brand, retail_price, department, sku, distribution_center_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

            String[] row;
            reader.readNext(); // Skip header

            int rowCount = 0;

            while ((row = reader.readNext()) != null) {
                pstmt.setInt(1, Integer.parseInt(row[0])); // id
                pstmt.setDouble(2, Double.parseDouble(row[1])); // cost
                pstmt.setString(3, row[2]); // category
                pstmt.setString(4, row[3]); // name
                pstmt.setString(5, row[4]); // brand
                pstmt.setDouble(6, Double.parseDouble(row[5])); // retail_price
                pstmt.setString(7, row[6]); // department
                pstmt.setString(8, row[7]); // sku
                pstmt.setInt(9, Integer.parseInt(row[8])); // distribution_center_id

                pstmt.addBatch();
                rowCount++;

                // Batch insert every 100 rows
                if (rowCount % 100 == 0) {
                    pstmt.executeBatch();
                }
            }

            // Execute remaining
            pstmt.executeBatch();

            System.out.println("✅ Loaded " + rowCount + " records into the database.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
