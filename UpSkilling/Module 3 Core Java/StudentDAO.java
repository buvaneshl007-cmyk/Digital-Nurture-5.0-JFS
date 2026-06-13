import java.sql.*;

public class StudentDAO {
    private String url = "jdbc:sqlite:students.db";
    
    public void insertStudent(String name, double marks) {
        try {
            Connection conn = DriverManager.getConnection(url);
            String insertSQL = "INSERT INTO students (name, marks) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, name);
            pstmt.setDouble(2, marks);
            
            pstmt.executeUpdate();
            System.out.println("Student inserted successfully!");
            
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }
    
    public void updateStudentMarks(int id, double marks) {
        try {
            Connection conn = DriverManager.getConnection(url);
            String updateSQL = "UPDATE students SET marks = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(updateSQL);
            pstmt.setDouble(1, marks);
            pstmt.setInt(2, id);
            
            pstmt.executeUpdate();
            System.out.println("Student updated successfully!");
            
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.insertStudent("Alice", 85.5);
        dao.insertStudent("Bob", 90.0);
        dao.updateStudentMarks(1, 88.0);
    }
}
