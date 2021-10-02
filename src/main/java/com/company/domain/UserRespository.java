package com.company.domain;

import java.util.List;

 public interface UserRespository {

     User findById(Long id);
     User findByUsername(String username);
     User findByEmail(String email);
     List<User> findAll();
     User save(User user);
     User update(Long id, String username, String password, String email, Double balance);
     User remove(Long id);


}
