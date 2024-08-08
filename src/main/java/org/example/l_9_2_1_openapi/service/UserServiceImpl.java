package org.example.l_9_2_1_openapi.service;

import com.github.javafaker.Faker;
import org.example.openapi.model.UserModel;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final List<UserModel> userModels = new ArrayList<>();

    private static final Faker faker = new Faker();
    private static final SecureRandom secureRandom = new SecureRandom();

    static {
        for (int i = 0; i < 10; i++) {
            UserModel user = new UserModel()
                    .id(Math.abs(secureRandom.nextLong()))
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .birthday(LocalDate.ofInstant(faker.date().birthday().toInstant(), ZoneId.systemDefault()))
                    .address(faker.address().fullAddress());
            userModels.add(user);
        }
    }

    @Override
    public List<UserModel> getAll() {
        return userModels;
    }

    @Override
    public UserModel getById(Long id) {
        return userModels.stream()
                .filter(userModel -> userModel.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public UserModel create(UserModel createUser) {
        createUser.setId(Math.abs(secureRandom.nextLong()));
        userModels.add(createUser);
        return createUser;
    }

    @Override
    public UserModel update(Long id, UserModel updateUser) {
        UserModel oldUser = getById(id);
        oldUser.setLastName(updateUser.getLastName());
        oldUser.setFirstName(updateUser.getFirstName());
        oldUser.setAddress(updateUser.getAddress());
        oldUser.setBirthday(updateUser.getBirthday());
        return oldUser;
    }

    @Override
    public void delete(Long id) {
        userModels.removeIf(userModel -> userModel.getId().equals(id));
    }


}
