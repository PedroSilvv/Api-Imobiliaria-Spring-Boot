package com.example.apiimobiliaria.repositories;

import com.example.apiimobiliaria.models.UserModel;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Override
    List<UserModel> findAll();
    UserModel findById(long id);
}
