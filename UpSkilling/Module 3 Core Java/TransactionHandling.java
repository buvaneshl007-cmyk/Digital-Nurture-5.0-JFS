import java.sql.*;

public class TransactionHandling {
    
    public static void transferFunds(int fromAccountId, int toAccountId, double amount) {
        String url = "jdbc:sqlite:bank.db";
        
        try {
            Connection conn = DriverManager.getConnection(url);
            
            conn.setAutoCommit(false);
            
            Statement stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts (" +
                    "id INTEGER PRIMARY KEY," +
                    "balance REAL)";
            stmt.execute(createTableSQL);
            stmt.close();
            
            String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromAccountId);
            int debitRows = debitStmt.executeUpdate();
            debitStmt.close();
            
            String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            PreparedStatement creditStmt = conn.prepareStatement(creditSQL);
            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toAccountId);
            int creditRows = creditStmt.executeUpdate();
            creditStmt.close();
            
            if (debitRows > 0 && creditRows > 0) {
                conn.commit();
                System.out.println("Transfer successful!");
            } else {
                conn.rollback();
                System.out.println("Transfer failed!");
            }
            
            conn.close();
        } catch (SQLException e) {
            System.out.println("Transaction error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        transferFunds(1, 2, 100.0);
    }
}
