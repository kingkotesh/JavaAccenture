package com.demo.Movies_Database_Managemenet_System;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class ActorOperations implements OperationsInterface {

    private static Scanner scanner;

    @Override
    public void insert(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            // Create a new actor object
            Actor newActor = new Actor();

            // Input actor details
            System.out.println("Enter ActorId:");
            int actorId = scanner.nextInt();
            System.out.println("Enter MovieId:");
            int movieId = scanner.nextInt();
            System.out.println("Enter Actor Name:");
            scanner.nextLine(); // Consume newline
            String actorName = scanner.nextLine();
            System.out.println("Enter Role:");
            String role = scanner.nextLine();

            // Set actor details
            newActor.setId(new ActorId(actorId, movieId));
            newActor.setActorName(actorName);
            newActor.setRole(role);

            // Save the new actor
            session.save(newActor);

            // Commit the transaction
            transaction.commit();

            System.out.println("New actor inserted successfully!");
        } catch (Exception e) {
            System.err.println("Error inserting actor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveAll(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve all actors
            List<Actor> actors = session.createQuery("FROM Actor", Actor.class).list();

            // Display each actor's information
            for (Actor actor : actors) {
                System.out.println(actor);
            }

            transaction.commit();
        }
    }

    @Override
    public void update(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);
            
            // Retrieve actor details to update
            System.out.println("Enter ActorId:");
            int actorId = scanner.nextInt();
            System.out.println("Enter MovieId:");
            int movieId = scanner.nextInt();
            
            ActorId idToUpdate = new ActorId(actorId, movieId);
            
            // Retrieve the actor by its composite key
            Actor actorToUpdate = session.get(Actor.class, idToUpdate);

            if (actorToUpdate != null) {
                while (true) {
                    // Display menu options
                    System.out.println("Menu:");
                    System.out.println("1. Actor Name");
                    System.out.println("2. Role");
                    System.out.println("3. Exit");
                    // Update actor's information
                    // Read user choice
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();

                    // Perform action based on user choice
                    switch (choice) {
                        case 1:
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter new Actor Name:");
                            String newActorName = scanner.nextLine();
                            actorToUpdate.setActorName(newActorName);
                            break;
                        case 2:
                            scanner.nextLine(); // Consume newline
                            System.out.println("Enter new Role:");
                            String newRole = scanner.nextLine();
                            actorToUpdate.setRole(newRole);
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            session.update(actorToUpdate);
                            transaction.commit();
                            System.out.println("Actor updated successfully!");
                            return; // Exit the method
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Actor not found!");
            }
        } catch (Exception e) {
            System.err.println("Error updating actor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(SessionFactory factory) {
    	try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            scanner = new Scanner(System.in);

            // Retrieve an actor by ActorId and MovieId
            System.out.println("Enter ActorId to delete:");
            int actorId = scanner.nextInt();
            System.out.println("Enter MovieId to delete:");
            int movieId = scanner.nextInt();
            ActorId id = new ActorId(actorId, movieId);
            Actor actor = session.get(Actor.class, id);

            if (actor != null) {
                // Delete the actor if found
                session.delete(actor);
                transaction.commit();
                System.out.println("Actor deleted successfully!");
            } else {
                System.out.println("Actor with given ActorId and MovieId does not exist!");
            }
        } catch (Exception e) {
            System.err.println("Error deleting actor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
