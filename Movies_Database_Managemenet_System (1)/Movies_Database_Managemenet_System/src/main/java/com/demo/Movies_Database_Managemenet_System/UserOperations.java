package com.demo.Movies_Database_Managemenet_System;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            // Validate and set email
            String email;
            do {
                System.out.println("Enter Email");
                email = scanner.nextLine();
                if (!isValidEmail(email)) {
                    System.out.println("Invalid email format. Please enter a valid email address ending with @gmail.com.");
                }
            } while (!isValidEmail(email));
            newUser.setEmail(email);

            // Validate and set phone number
            String phoneNumber;
            do {
                System.out.println("Enter Phone Number");
                phoneNumber = scanner.nextLine();
                if (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Invalid phone number format. Please enter a valid phone number.");
                }
            } while (!isValidPhoneNumber(phoneNumber));
            newUser.setPhoneNumber(phoneNumber);

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
    public void delete(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a user by ID
            System.out.println("Enter id to delete");
            int userId = scanner.nextInt();
            User user = session.get(User.class, userId);

            if (user != null) {
                // Delete associated login data first
                Login login = session.createQuery("FROM Login WHERE userName = :userName", Login.class)
                                    .setParameter("userName", user.getUserName())
                                    .uniqueResult();
                if (login != null) {
                    session.delete(login);
                }

                // Then delete the user
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

    
    
    @Override
    public void update(SessionFactory factory) {
        // Implementation for update method
    	
    }

   

    // Email validation method
    private static boolean isValidEmail(String email) {
        String emailRegex = "^.+@gmail\\.com$"; // Ensures @gmail.com is at the end
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Phone number validation method
    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,3}[- ]?)?\\d{10}$"; // Simple phone number validation
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
