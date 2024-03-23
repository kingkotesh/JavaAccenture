package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class UserOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new user object
            User newUser = new User();
            System.out.println("Enter User Name");
            newUser.setUserName(scanner.nextLine());
            System.out.println("Enter Email");
            newUser.setEmail(scanner.nextLine());
            System.out.println("Enter Phone Number");
            newUser.setPhoneNumber(scanner.nextLine());
            
            // Save the new user
            session.save(newUser);

            // Commit the transaction
            transaction.commit();

            System.out.println("New user inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all users using HQL
            List<User> users = session.createQuery("FROM User", User.class).list();

            // Display each user's information
            for (User user : users) {
                System.out.println(user);
            }

            transaction.commit();
        }
    }

    @Override
    public void update(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a user by ID
            System.out.println("Enter id to update");
            User user = session.get(User.class, scanner.nextInt()); // Assuming ID 2 for example

            while (true) {
                // Display menu options
                System.out.println("Menu:");
                System.out.println("1. User Name");
                System.out.println("2. Email");
                System.out.println("3. Phone Number");
                System.out.println("4. Exit");
                // Update user's information
                // Read user choice
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                // Consume newline character
                scanner.nextLine();

                // Perform action based on user choice
                switch (choice) {
                    case 1:
                        System.out.println("Enter new user name:");
                        user.setUserName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter new email:");
                        user.setEmail(scanner.nextLine());
                        break;
                    case 3:
                        System.out.println("Enter new phone number:");
                        user.setPhoneNumber(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        session.update(user);
                        transaction.commit();
                        System.out.println("User updated successfully!");
                        return; // Exit the method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            // Save the updated user
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a user by ID
            System.out.println("Enter id to delete");
            User user = session.get(User.class, scanner.nextInt());

            if (user != null) {
                // Delete the user if found
                session.delete(user);
                transaction.commit();
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("User does not exist!");
            }
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    
    }
}
