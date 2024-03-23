package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class MovieTypeOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new movie type object
            MovieType newMovieType = new MovieType();
            System.out.println("Enter id");
            newMovieType.setGenreid(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Enter genre");
            newMovieType.setGenre(scanner.nextLine());

            // Save the new movie type
            session.save(newMovieType);

            // Commit the transaction
            transaction.commit();

            System.out.println("New movie type inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting movie type: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all movie types using HQL
            List<MovieType> movieTypes = session.createQuery("FROM MovieType", MovieType.class).list();

            // Display each movie type's information
            for (MovieType movieType : movieTypes) {
                System.out.println(movieType);
            }

            transaction.commit();
        }
    }

    @Override
    public void update(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a movie type by ID
            System.out.println("Enter id to update");
            MovieType movieType = session.get(MovieType.class, scanner.nextInt());

            // Update movie type's information
            scanner.nextLine();
            System.out.println("Enter genre");
            movieType.setGenre(scanner.nextLine());
            session.update(movieType);

            transaction.commit();
            System.out.println("Movie type updated successfully!");
        } catch (Exception e) {
            System.err.println("Error updating movie type: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a movie type by ID
            System.out.println("Enter id to delete");
            MovieType movieType = session.get(MovieType.class, scanner.nextInt());

            // Delete the movie type
            session.delete(movieType);

            transaction.commit();
            System.out.println("Movie type deleted successfully!");
        } catch (Exception e) {
            System.err.println("Error deleting movie type: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
