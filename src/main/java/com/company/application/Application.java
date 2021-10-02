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
        app.cors();
        con = MySQL.connect();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClearBnB");
        EntityManager em = emf.createEntityManager();

        UserHandler userHandler = new UserHandler(app, em);

    }

}
