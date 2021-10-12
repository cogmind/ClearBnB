package com.company.application;

import com.company.domain.User;
import com.company.infrastructure.UserRepositoryImpl;
import com.company.utilities.HashPassword;

import express.Express;
import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserHandler {
    private final Express app;
    private final EntityManager entityManager;
    private final UserRepositoryImpl userRepository;

    public UserHandler(Express app, EntityManager entityManager) {
        this.app = app;
        this.entityManager = entityManager;
        this.userRepository = new UserRepositoryImpl(this.entityManager);
        this.register();
        this.login();
        this.logout();
        this.whoami();
        this.getId();
        this.remove();
    }

    private void getId() {
            app.get("/api/userid", (req, res) -> {
                System.out.println("Getting user id...");
                res.append("Access-Control-Allow-Origin", "http://localhost:3000");
                res.append("Access-Control-Allow-Credentials", "true");

                User user = req.session("current-user");
                System.out.println(user.getUserId());
                res.send(user.getUserId());
            });
    }

    private void register() {
        // Register user
        app.post("/api/register", (req, res) -> {
            User user = req.body(User.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            try{
                User seeIfTheUserExists = userRepository.getByUsername(user.getUsername());
                if(seeIfTheUserExists.getClass().equals(User.class)){

                    res.json("That username is already taken.");
                }
            }
            catch(Exception e){
                if(e.getMessage().equals("No entity found for query")){
                    res.json("Made a new user!");
                    double defaultBalance = 40000.00;

                    // Check if email is not taken
                    User exists;
                    try {
                        exists = this.userRepository.getByEmail(user.getEmail());
                        res.json(Map.of("error", "User already exists"));

                    } catch(NoResultException nre) {

                        // Hash password (encrypt password)
                        String hashedPassword = HashPassword.hash(user.getPassword());

                        user.setPassword(hashedPassword);
                        user.setBalance(defaultBalance);

                        // Save user to db
                        userRepository.save(user);
                        // Log in
                        req.session("current-user", user);
                        System.out.println(userRepository.getAll());
                        res.json("User created");
                    }
                }
                else{
                    System.out.println(e.getMessage());
                    res.json(e.getMessage());
                }
            }
         });
    }

    private void update() {
        app.post("/api/updateuser", (req, res) -> {
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            User user = req.body(User.class);
            // Update a user
            userRepository.update(user.getUserId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getBalance());
            System.out.println(userRepository.getAll());
        });
    }

    private void remove() {
        // Remove a user
        app.post("/api/removeuser", (req, res) -> {
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            User user = req.body(User.class);
            userRepository.remove(user.getUserId());
            System.out.println(userRepository.getAll());
        });
    }

    private void login() {
        // login user
        app.post("/api/login", (req, res) -> {
            User user = req.body(User.class);
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");

            User exists = this.userRepository.getByEmail(user.getEmail());

            if (exists == null) {
                res.json(Map.of("error", "Bad credentials"));
                return;
            }

            // validate password
            if (HashPassword.match(user.getPassword(), exists.getPassword())) {
                // save user in session, to remember logged in state
                System.out.println("LOGIN Setting session storage to user");
                req.session("current-user", exists);
                res.json(exists);

            } else {
                res.json(Map.of("error", "Bad credentials"));
            }
        });
    }

    private void whoami() {
        // who am i? get logged in user
        app.get("/api/whoami", (req, res) -> {
            // return user saved in session
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            res.json(req.session("current-user"));
        });
    }

    private void logout(){
        // Logout user
        app.get("/api/logout", (req, res) -> {
            // Remove user from session
            res.append("Access-Control-Allow-Origin", "http://localhost:3000");
            res.append("Access-Control-Allow-Credentials", "true");
            req.session("current-user", null);

            res.json(Map.of("ok", "logged out"));
        });
    }
}