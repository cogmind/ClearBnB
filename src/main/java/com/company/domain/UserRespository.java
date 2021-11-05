package com.company.domain;

import java.util.List;

 public interface UserRespository {

     User getById(Long id);
     User getByUsername(String username);
     User getByEmail(String email);
     List<User> getAll();
     User save(User user);
     User update(Long id, String username, String password, String email, Double balance);
     User remove(Long id);
}
