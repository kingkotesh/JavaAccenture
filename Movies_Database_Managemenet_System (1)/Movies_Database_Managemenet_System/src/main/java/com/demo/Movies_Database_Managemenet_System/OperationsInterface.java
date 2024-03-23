package com.demo.Movies_Database_Managemenet_System;
import org.hibernate.SessionFactory;
public interface OperationsInterface {

    // Insert operation
    void insert(SessionFactory factory);

    // Retrieve operation
    void retrieveAll(SessionFactory factory);

    // Update operation
    void update(SessionFactory factory);

    // Delete operation
    void delete(SessionFactory factory);
}
