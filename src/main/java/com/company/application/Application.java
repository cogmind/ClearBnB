package com.company.application;

import express.Express;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;

public class Application {
    // Java Express
    Express app = new Express();
    Connection con;

    public Application() {
        //app.devLogging();
        app.cors();
        app.listen(4000);
        con = MySQL.connect();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClearBnB");
        EntityManager em = emf.createEntityManager();

        UserHandler userHandler = new UserHandler(app, em);
        ListingHandler listingHandler = new ListingHandler(app, em);
        BookingHandler bookingHandler = new BookingHandler(app, em);
        //ReviewHandler
        //ChatHandler
    }

}
