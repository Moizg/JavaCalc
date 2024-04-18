package JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class databaseConnector {
	static String connString = "jdbc:sqlserver://localhost:1433;databaseName=CalculatorDB;trustServerCertificate=true;integratedSecurity=true";
	static String user = "admin";
	static String password = "";

	public void saveAdditionResult(double num1, double num2, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO Addition_table (operand1, operand2, result) VALUES (?, ?, ?)")) {
	        stmt.setDouble(1, num1);
	        stmt.setDouble(2, num2);
	        stmt.setDouble(3, result);
	        stmt.executeUpdate();
	        System.out.println("Addition result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving addition result: " + e.getMessage());
	    }
	}
	
	public void saveSubtractionResult(double num1, double num2, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO Subtraction_table (operand1, operand2, result) VALUES (?, ?, ?)")) {
	        stmt.setDouble(1, num1);
	        stmt.setDouble(2, num2);
	        stmt.setDouble(3, result);
	        stmt.executeUpdate();
	        System.out.println("Subtraction result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving subtraction result: " + e.getMessage());
	    }
	}
	
	public void saveMultiplicationResult(double num1, double num2, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO Multiplication_table (operand1, operand2, result) VALUES (?, ?, ?)")) {
	        stmt.setDouble(1, num1);
	        stmt.setDouble(2, num2);
	        stmt.setDouble(3, result);
	        stmt.executeUpdate();
	        System.out.println("Multiplication result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving multiplication result: " + e.getMessage());
	    }
	}

	public void saveDivisionResult(double num1, double num2, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO Division_table (operand1, operand2, result) VALUES (?, ?, ?)")) {
	        stmt.setDouble(1, num1);
	        stmt.setDouble(2, num2);
	        stmt.setDouble(3, result);
	        stmt.executeUpdate();
	        System.out.println("Division result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving division result: " + e.getMessage());
	    }
	}
	
	public void saveSquareRootResult(double num, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO SquareRoot_table (Operand, Result) VALUES (?, ?)")) {
	        stmt.setDouble(1, num);
	        stmt.setDouble(2, result);
	        stmt.executeUpdate();
	        System.out.println("Square root result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving square root result: " + e.getMessage());
	    }
	}
	
	public void saveSquareResult(double num, double result) {
	    try (Connection conn = DriverManager.getConnection(connString, user, password);
	         PreparedStatement stmt = conn.prepareStatement("INSERT INTO Square_table (Operand, Result) VALUES (?, ?)")) {
	        stmt.setDouble(1, num);
	        stmt.setDouble(2, result);
	        stmt.executeUpdate();
	        System.out.println("Square result saved successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error saving square result: " + e.getMessage());
	    }
	}
	
	public void clearAllTables() {
	    try (Connection conn = DriverManager.getConnection(connString, user, password)) {
	        // Delete all data from AdditionResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Addition_table")) {
	            stmt.executeUpdate();
	        }
	        // Delete all data from SubtractionResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Subtraction_table")) {
	            stmt.executeUpdate();
	        }
	        // Delete all data from MultiplicationResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Multiplication_table")) {
	            stmt.executeUpdate();
	        }
	        // Delete all data from DivisionResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Division_table")) {
	            stmt.executeUpdate();
	        }
	        // Delete all data from SquareRootResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM SquareRoot_table")) {
	            stmt.executeUpdate();
	        }
	        // Delete all data from SquareResults table
	        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM Square_table")) {
	            stmt.executeUpdate();
	        }
	        
	        System.out.println("All tables cleared successfully.");
	    } catch (SQLException e) {
	        System.out.println("Error clearing tables: " + e.getMessage());
	    }
	}
	
	public List<String> getAdditionHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Addition_table")) {
            while (rs.next()) {
                double operand1 = rs.getDouble("operand1");
                double operand2 = rs.getDouble("operand2");
                double result = rs.getDouble("result");
                history.add(operand1 + " + " + operand2 + " = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving addition history: " + e.getMessage());
        }
        return history;
    }
	
	public List<String> getSubtractionHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Subtraction_table")) {
            while (rs.next()) {
                double operand1 = rs.getDouble("operand1");
                double operand2 = rs.getDouble("operand2");
                double result = rs.getDouble("result");
                history.add(operand1 + " - " + operand2 + " = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving subtraction history: " + e.getMessage());
        }
        return history;
    }

    public List<String> getMultiplicationHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Multiplication_table")) {
            while (rs.next()) {
                double operand1 = rs.getDouble("operand1");
                double operand2 = rs.getDouble("operand2");
                double result = rs.getDouble("result");
                history.add(operand1 + " * " + operand2 + " = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving multiplication history: " + e.getMessage());
        }
        return history;
    }

    public List<String> getDivisionHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Division_table")) {
            while (rs.next()) {
                double operand1 = rs.getDouble("operand1");
                double operand2 = rs.getDouble("operand2");
                double result = rs.getDouble("result");
                history.add(operand1 + " / " + operand2 + " = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving division history: " + e.getMessage());
        }
        return history;
    }
    
    public List<String> getSquareRootHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SquareRoot_table")) {
            while (rs.next()) {
                double operand = rs.getDouble("operand");
                double result = rs.getDouble("result");
                history.add("√" + operand + " = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving square root history: " + e.getMessage());
        }
        return history;
    }

    public List<String> getSquareHistory() {
        List<String> history = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(connString, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Square_table")) {
            while (rs.next()) {
                double operand = rs.getDouble("operand");
                double result = rs.getDouble("result");
                history.add(operand + "² = " + result);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving square history: " + e.getMessage());
        }
        return history;
    }
}