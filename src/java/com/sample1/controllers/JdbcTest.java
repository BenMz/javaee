/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample1.controllers;
import java.sql.*;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class JdbcTest {

	public static void main(String[] args) throws SQLException {

		Connection myConn = null;
		Statement myStmt = null;
		PreparedStatement myStmt2 = null;
		ResultSet myRs = null;
		
		try {
                        String[] values = {"jdbc:mysql://localhost:3306/demo?useSSL=false", "root" , ""};
			// 1. Get a connection to database
			myConn = DriverManager.getConnection(values[0],values[1],values[2]);
			
			System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			myStmt = myConn.createStatement();
                           
                        //INSERT
int rowsAffected = myStmt.executeUpdate(
				"insert into employees " +
				"(last_name, first_name, email, department, salary) " + 
				"values " + 
				"('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");
                         ///UPDATE
                         
			
			int rowsAffected2 = myStmt.executeUpdate(
					"update employees " +
					"set email='john.doe@luv2code.com' " + 
					"where last_name='Doe' and first_name='John'");
                        
			int rowsAffected3 = myStmt.executeUpdate(
					"delete from employees " +
					"where last_name='Doe' and first_name='John'");
			
                        // 3. Execute SQL query
			myRs = myStmt.executeQuery("select * from employees");
                        
			// 2. Prepare statement
			myStmt2 = myConn.prepareStatement("select * from employees where salary > ? and department=?");
			
			// 3. Set the parameters
			myStmt2.setDouble(1, 80000);
			myStmt2.setString(2, "Legal");
			
			// 4. Execute SQL query
			myRs = myStmt2.executeQuery();
			
			// 5. Display the result set
			display(myRs);
		
			//
			// Reuse the prepared statement:  salary > 25000,  department = HR
			//

			System.out.println("\n\nReuse the prepared statement:  salary > 25000,  department = HR");
			
			// 6. Set the parameters
			myStmt2.setDouble(1, 25000);
			myStmt2.setString(2, "HR");
			
			// 7. Execute SQL query
			myRs = myStmt2.executeQuery();
			
			// 8. Display the result set
			display(myRs);
			
			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}
	}
        	private static void display(ResultSet myRs) throws SQLException {
		while (myRs.next()) {
			String lastName = myRs.getString("last_name");
			String firstName = myRs.getString("first_name");
			double salary = myRs.getDouble("salary");
			String department = myRs.getString("department");
			
			System.out.printf("%s, %s, %.2f, %s\n", lastName, firstName, salary, department);
		}
	}

}
