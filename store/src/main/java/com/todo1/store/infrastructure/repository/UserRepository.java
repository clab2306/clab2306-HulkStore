package com.todo1.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo1.store.infrastructure.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
