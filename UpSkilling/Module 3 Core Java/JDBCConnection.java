import java.sql.*;

public class JDBCConnection {
    public static void main(String[] args) {
        // For SQLite (embedded database, easier for demo)
        String url = "jdbc:sqlite:students.db";
        
        try {
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Connected to the database successfully!");
            
            Statement stmt = conn.createStatement();
            
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "marks REAL)";
            stmt.execute(createTableSQL);
            
            String query = "SELECT * FROM students";
            ResultSet rs = stmt.executeQuery(query);
            
            System.out.println("Students:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double marks = rs.getDouble("marks");
                System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
