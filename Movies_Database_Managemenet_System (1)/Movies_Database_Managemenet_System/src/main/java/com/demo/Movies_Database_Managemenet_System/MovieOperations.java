package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class MovieOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new movie object
            Movie newMovie = new Movie();
            System.out.println("Enter Movie Name");
            newMovie.setMovieName(scanner.nextLine());
            System.out.println("Enter Year of Release");
            newMovie.setYearRelease(scanner.nextInt());
            scanner.nextLine(); // Consume newline character
            System.out.println("Enter Status");
            newMovie.setStatus(scanner.nextLine());
            System.out.println("Enter Trailer URL");
            newMovie.setTrailer(scanner.nextLine());
            System.out.println("Enter Rating");
            newMovie.setRating(scanner.nextDouble());

            // Assume you have MovieType and Director objects already created for this movie
            // Set the genre and director
            // For simplicity, let's assume genreId and directorId are known
            System.out.println("Enter genreId");
            MovieType genre = session.get(MovieType.class,scanner.nextInt() ); // Replace genreId with actual genre ID
            System.out.println("Enter directorId");
            Director director = session.get(Director.class,scanner.nextInt() ); // Replace directorId with actual director ID
            newMovie.setGenre(genre);
            newMovie.setDirector(director);

            // Save the new movie
            session.save(newMovie);

            // Commit the transaction
            transaction.commit();

            System.out.println("New movie inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting movie: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all movies using HQL
            List<Movie> movies = session.createQuery("FROM Movie", Movie.class).list();

            // Display each movie's information
            for (Movie movie : movies) {
                System.out.println(movie);
            }

            transaction.commit();
        }
    }

    @Override
    public void update(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a movie by ID
            System.out.println("Enter id to update");
            Movie movie = session.get(Movie.class, scanner.nextInt()); // Assuming ID 2 for example

            while (true) {
                // Display menu options
                System.out.println("Menu:");
                System.out.println("1. Movie Name");
                System.out.println("2. Year of Release");
                System.out.println("3. Status");
                System.out.println("4. Trailer");
                System.out.println("5. Rating");
                System.out.println("6. Exit");
                // Update movie's information
                // Read user choice
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                // Perform action based on user choice
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        System.out.println("Enter name");
                        movie.setMovieName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.println("Enter year of release");
                        movie.setYearRelease(scanner.nextInt());
                        break;
                    case 3:
                        scanner.nextLine();
                        System.out.println("Enter status");
                        movie.setStatus(scanner.nextLine());
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.println("Enter trailer");
                        movie.setTrailer(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Enter rating");
                        movie.setRating(scanner.nextDouble());
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        session.update(movie);
                        transaction.commit();
                        System.out.println("Movie updated successfully!");
                        return; // Exit the main method
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
            // Save the updated movie

        }
    }

    @Override
    public void delete(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Retrieve a movie by ID
            System.out.println("Enter id to delete");
            Movie movie = session.get(Movie.class, scanner.nextInt());

            if (movie != null) {
                // Delete the movie if found
                session.delete(movie);
                transaction.commit();
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("Does not exist!");
            }
        }
    }
}
