package org.example.l_9_2_1_openapi.service;

import org.example.openapi.model.UserModel;

import java.util.List;

public interface UserService {
    List<UserModel> getAll();
    UserModel getById(Long id);
    UserModel create(UserModel createUser);
    UserModel update(Long id, UserModel updateUser);
    void delete(Long id);
}
