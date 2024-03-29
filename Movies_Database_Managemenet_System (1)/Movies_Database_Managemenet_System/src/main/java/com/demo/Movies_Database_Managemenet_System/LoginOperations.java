package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class LoginOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new login object
            Login newLogin = new Login();

            // Retrieve the last inserted user
            User lastInsertedUser = (User) session.createQuery("FROM User ORDER BY id DESC")
                                                  .setMaxResults(1)
                                                  .uniqueResult();

            if (lastInsertedUser != null) {
                newLogin.setUser(lastInsertedUser); // Set the last inserted user for the login
                System.out.println("Enter Password");
                newLogin.setPassword(scanner.nextLine());
                
                // Save the new login
                session.save(newLogin);

                // Commit the transaction
                transaction.commit();

                System.out.println("New login inserted successfully!");
            } else {
                System.out.println("No users found in the database!");
            }
        } catch (Exception e) {
            System.err.println("Error inserting login: " + e.getMessage());
            e.printStackTrace();
        }
    }

	@Override
	public void retrieveAll(SessionFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SessionFactory factory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SessionFactory factory) {
		// TODO Auto-generated method stub
		
	}
   

}
