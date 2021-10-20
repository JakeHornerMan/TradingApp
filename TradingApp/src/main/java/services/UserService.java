package services;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import repos.UserRepo;

import java.util.List;

@Service
@Configurable
public class UserService {

    @Autowired
    private UserRepo repo;

    public List<User> listAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(int user_id) {
        return repo.findById(user_id).get();
    }

    public void delete(int userID) {
        repo.deleteById(userID);
    }



}

