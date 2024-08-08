package org.example.l_9_2_1_openapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.l_9_2_1_openapi.service.UserService;
import org.example.openapi.api.UserApi;
import org.example.openapi.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserModel> createUser(UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userModel));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.delete(id);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @Override
    public ResponseEntity<UserModel> getByIdUser(Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    public ResponseEntity<UserModel> updateUser(Long id, UserModel userModel) {
        return ResponseEntity.ok(userService.update(id, userModel));
    }
}
