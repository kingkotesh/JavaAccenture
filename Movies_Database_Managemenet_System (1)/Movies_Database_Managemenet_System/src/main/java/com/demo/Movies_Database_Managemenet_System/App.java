package com.demo.Movies_Database_Managemenet_System;
import java.util.Scanner;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;


public class App {
    private static Scanner scanner;
    private static String user1="sree";
    private static String password1="S&ee1";
    private static String user2="king";
    private static String password2="K!ng2";
	public static void main(String[] args) {
		SessionFactory factory =null;
		scanner = new Scanner(System.in);
		while(true) {
        	System.out.println("1. Create User Account");
            System.out.println("2. Admin Login");
            System.out.println("3. User Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            // Perform action based on user choice
            switch (choice) {
                case 1:
                	factory=create();
                    break;
                case 2:
                	factory=adminLogin();
                    break;
                case 3:
                	factory=userLogin();
                    break;
                case 4:
                	System.out.println("Exited");
                	if (factory != null && !factory.isClosed()) {
                        factory.close();
                    }
                    scanner.close();
                	return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
		}
		
        
        
        
    }
	private static SessionFactory userLogin() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try (Session session = factory.openSession()) {
	        Transaction transaction = session.beginTransaction();
	        scanner = new Scanner(System.in);
	        // Create a new login object
	        
	        // Get the user name from the user
	        System.out.println("Enter User Name");
	        String userName = scanner.nextLine();
	        
	        // Get the password from the user
	        System.out.println("Enter Password");
	        String password = scanner.nextLine();
	        
	        // Check if the entered username and password exist in the Login table
	        Query<Login> query = session.createQuery("FROM Login WHERE user.userName = :userName AND password = :password", Login.class)
	                                    .setParameter("userName", userName)
	                                    .setParameter("password", password);
	        
	        Login login = query.uniqueResult();
	        if (login != null) {
	            System.out.println("Login successful!");
	            scanner = new Scanner(System.in);
				while(true) {
		        	System.out.println("Menu:");
		            System.out.println("1. Directors");
		            System.out.println("2. MovieTypes");
		            System.out.println("3. Movie");
		            System.out.println("4. Actor");
		            System.out.println("5. Exit");
		            System.out.print("Enter your choice: ");
		            int choice = scanner.nextInt();
		            
		            // Perform action based on user choice
		            switch (choice) {
		                case 1:
		                	DirectorOperations d = new DirectorOperations();
		                	d.retrieveAll(factory);
		                    break;
		                case 2:
		                	MovieTypeOperations mt = new MovieTypeOperations();
		                	mt.retrieveAll(factory);
		                    break;
		                case 3:
		                	MovieOperations m = new MovieOperations();
		                	m.retrieveAll(factory);
		                    break;
		                case 4:
		                	ActorOperations ao = new ActorOperations();
		                	ao.retrieveAll(factory);
		                    break;
		                case 5:
		                    System.out.println("Exited");
		                    return factory; // Exit the main method
		                default:
		                    System.out.println("Invalid choice. Please try again.");
		            }
		        }
	        } else {
	            System.out.println("Invalid username or password!");
	            
	        }
	        
	        transaction.commit();
	    } catch (Exception e) {
	        System.err.println("Error inserting login: " + e.getMessage());
	        e.printStackTrace();
	    }
		return null;
	}
	private static SessionFactory adminLogin() {
		System.out.println("Enter UserName");
		String user=scanner.nextLine();
		System.out.println("Enter Password");
		String pass=scanner.nextLine();
		if ((user.equals(user1) || user.equals(user2)) && (pass.equals(password1) || pass.equals(password2))) {
			 
			// Obtain a Hibernate SessionFactory
			SessionFactory factory = null;
			scanner = new Scanner(System.in);
			while(true) {
	        	System.out.println("Menu:");
	            System.out.println("1. Directors");
	            System.out.println("2. MovieTypes");
	            System.out.println("3. Movie");
	            System.out.println("4. Actor");
	            System.out.println("5. User");
	            //System.out.println("6. Login");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            int choice = scanner.nextInt();

	            // Perform action based on user choice
	            switch (choice) {
	                case 1:
	                	factory=directorTable();
	                    break;
	                case 2:
	                	factory=movieTypeTable();
	                    break;
	                case 3:
	                	factory=movieTable();
	                    break;
	                case 4:
	                	factory=actor();
	                    break;
	                case 5:
	                	factory=user();
	                    break;
	                case 6:
	                    System.out.println("Exited");
	                    return factory; // Exit the main method
	                default:
	                    System.out.println("Invalid choice. Please try again.");
	            }
	        }
		}
		else {
			System.out.println("Invalid username or password!");
		}
		return null;
	}
	
	
	private static SessionFactory create() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		UserOperations op = new UserOperations();
		op.insert(factory);
		LoginOperations op1 = new LoginOperations();
		op1.insert(factory);
		return factory;
	}

	private static SessionFactory user() {
		// Infinite loop for menu-driven CRUD operations
		SessionFactory factory = HibernateUtil.getSessionFactory();
		UserOperations op = new UserOperations();
        // Create a Scanner object to read user input
        scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Menu:");
            System.out.println("1. Retrieve Users");
            System.out.println("2. Update Users");
            System.out.println("3. Delete Users");
            System.out.println("4. Insert Users");
            System.out.println("5. Exit");

            // Read user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    op.retrieveAll(factory);
                    break;
                case 2:
                    op.update(factory);
                    break;
                case 3:
                    op.delete(factory);
                    break;
                case 4:
                    op.insert(factory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // Close resources
                    //factory.close();
                    return factory; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
        }
	}
	private static SessionFactory actor() {
		// Infinite loop for menu-driven CRUD operations
		SessionFactory factory = HibernateUtil.getSessionFactory();
		ActorOperations op = new ActorOperations();
        // Create a Scanner object to read user input
        scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Menu:");
            System.out.println("1. Retrieve Actors");
            System.out.println("2. Update Actors");
            System.out.println("3. Delete Actors");
            System.out.println("4. Insert Actors");
            System.out.println("5. Exit");

            // Read user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    op.retrieveAll(factory);
                    break;
                case 2:
                    op.update(factory);
                    break;
                case 3:
                    op.delete(factory);
                    break;
                case 4:
                    op.insert(factory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // Close resources
                    //factory.close();
                    return factory; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
        }
	}
	private static SessionFactory movieTable() {
		// Infinite loop for menu-driven CRUD operations
		SessionFactory factory = HibernateUtil.getSessionFactory();
		MovieOperations op = new MovieOperations();
        // Create a Scanner object to read user input
        scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Menu:");
            System.out.println("1. Retrieve Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. Insert Movie");
            System.out.println("5. Exit");

            // Read user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    op.retrieveAll(factory);
                    break;
                case 2:
                    op.update(factory);
                    break;
                case 3:
                    op.delete(factory);
                    break;
                case 4:
                    op.insert(factory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // Close resources
                    //factory.close();
                    return factory; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
        }
	}
	private static SessionFactory movieTypeTable() {
		// Infinite loop for menu-driven CRUD operations
		SessionFactory factory = HibernateUtil.getSessionFactory();
		MovieTypeOperations op = new MovieTypeOperations();
        // Create a Scanner object to read user input
        scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Menu:");
            System.out.println("1. Retrieve MovieType");
            System.out.println("2. Update MovieType");
            System.out.println("3. Delete MovieType");
            System.out.println("4. Insert MovieType");
            System.out.println("5. Exit");

            // Read user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    op.retrieveAll(factory);
                    break;
                case 2:
                    op.update(factory);
                    break;
                case 3:
                    op.delete(factory);
                    break;
                case 4:
                    op.insert(factory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // Close resources
                    //factory.close();
                    return factory; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
        }
	}
	private static SessionFactory directorTable() {
		// Infinite loop for menu-driven CRUD operations
		SessionFactory factory = HibernateUtil.getSessionFactory();
		DirectorOperations op = new DirectorOperations();
        // Create a Scanner object to read user input
        scanner = new Scanner(System.in);
        while (true) {
            // Display menu options
            System.out.println("Menu:");
            System.out.println("1. Retrieve Directors");
            System.out.println("2. Update Director");
            System.out.println("3. Delete Director");
            System.out.println("4. Insert Director");
            System.out.println("5. Exit");

            // Read user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    op.retrieveAll(factory);
                    break;
                case 2:
                    op.update(factory);
                    break;
                case 3:
                    op.delete(factory);
                    break;
                case 4:
                    op.insert(factory);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    // Close resources
                    //factory.close();
                    return factory; // Exit the main method
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        
        }
	}
}
