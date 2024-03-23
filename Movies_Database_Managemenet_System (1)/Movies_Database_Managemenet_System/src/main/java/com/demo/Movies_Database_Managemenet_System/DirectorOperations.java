package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class DirectorOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new director object
            Director newDirector = new Director();
            System.out.println("Enter id");
            newDirector.setDirectorid(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter name");
            newDirector.setDirectorname(scanner.nextLine());
            System.out.println("Enter hits");
            newDirector.setHitcount(scanner.nextInt());
            System.out.println("Enter flops");
            newDirector.setFlopcount(scanner.nextInt());
            System.out.println("Enter averages");
            newDirector.setAveragecount(scanner.nextInt());

            // Save the new director
            session.save(newDirector);

            // Commit the transaction
            transaction.commit();

            System.out.println("New director inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting director: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all directors using HQL
            List<Director> directors = session.createQuery("FROM Director", Director.class).list();

            // Display each director's information
            for (Director director : directors) {
                System.out.println(director);
            }

            transaction.commit();
        }
    }

    @Override
    public void update(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a director by ID
            System.out.println("Enter id to update");
            Director director = session.get(Director.class, scanner.nextInt()); // Assuming ID 2 for example

            while (true) {
                // Display menu options
                System.out.println("Menu:");
                System.out.println("1. Director Name");
                System.out.println("2. Hitcount");
                System.out.println("3. Flop count");
                System.out.println("4. Average count");
                System.out.println("5. Exit");
                // Update director's information
                // Read user choice
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                // Perform action based on user choice
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Enter name");
                        director.setDirectorname(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter hits");
                        director.setHitcount(scanner.nextInt());
                        break;
                    case 3:
                        System.out.println("Enter flops");
                        director.setFlopcount(scanner.nextInt());
                        break;
                    case 4:
                        System.out.println("Enter averages");
                        director.setAveragecount(scanner.nextInt());
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        session.update(director);

                        transaction.commit();

                        System.out.println("Director updated successfully!");
                        return; // Exit the main method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            // Save the updated director

        }
    }

    @Override
    public void delete(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a director by ID
            System.out.println("Enter id to delete");
            // Retrieve a director by ID
            Director director = session.get(Director.class,scanner.nextInt());

            if (director != null) {
                // Delete the director if found
                session.delete(director);
                transaction.commit();
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("Does not exist!");
            }
        }
    }
}
