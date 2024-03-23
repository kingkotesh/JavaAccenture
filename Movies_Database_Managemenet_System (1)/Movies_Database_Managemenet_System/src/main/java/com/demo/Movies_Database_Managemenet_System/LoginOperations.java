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
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all logins using HQL with a join fetch to include the user
            List<Object[]> results = session.createQuery(
                "SELECT l, l.user FROM Login l"
            ).list();

            // Display each login's information
            for (Object[] result : results) {
                Login login = (Login) result[0];
                User user = (User) result[1];
                System.out.println("Login ID: " + login.getLoginId() + ", Password: " + login.getPassword() + ", User Name: " + user.getUserName());
            }

            transaction.commit();
        }
    }




    @Override
    public void update(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a login by ID
            System.out.println("Enter id to update");
            Login login = session.get(Login.class, scanner.nextInt()); // Assuming ID 2 for example
            scanner.nextLine();
            System.out.println("Enter new password:");
            login.setPassword(scanner.next());
                   
            session.update(login);
            transaction.commit();
            System.out.println("Login updated successfully!");
        }
    }

    @Override
    public void delete(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a login by ID
            System.out.println("Enter id to delete");
            Login login = session.get(Login.class, scanner.nextInt());

            if (login != null) {
                // Delete the login if found
                session.delete(login);
                transaction.commit();
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("Does not exist!");
            }
        }
    }
}
