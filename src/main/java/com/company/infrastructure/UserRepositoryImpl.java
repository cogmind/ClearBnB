package com.company.infrastructure;

import com.company.domain.UserRespository;
import com.company.domain.User;

import jakarta.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRespository {

        private final EntityManager entityManager;

        public UserRepositoryImpl(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Override
        public User getById(Long id) {
            return entityManager.find(User.class, id);
        }

        @Override
        public User getByUsername(String username) {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }

        @Override
        public User getByEmail(String email) {
            return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        }

        @Override
        public List<User> getAll() {
            return entityManager.createQuery("FROM User").getResultList();
        }

        @Override
        public User save(User user) {
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(user);
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
        public User update(Long id, String username, String password, String email, Double balance) {
            User user = this.getById(id);
            try {
                entityManager.getTransaction().begin();
                if (username != null) {
                    user.setUsername(username);
                }
                if (password != null) {
                    user.setPassword(password);
                }
                if (email != null) {
                    user.setEmail(email);
                }
                if (balance != null) {
                    user.setBalance(balance);
                }
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    @Override
    public User remove(Long id) {
            User user = this.getById(id);
            try {
                entityManager.getTransaction().begin();
                // TODO Change to version management
                //entityManager.remove(user);
                entityManager.getTransaction().commit();
                return user;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
