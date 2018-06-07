package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.User;

public interface UserService {

     void registerNewUser(User user);

     User findByUserName(String name);

     void saveUser(User user);

     User findUserById(Long id);

     public void createUser(User user);

}
