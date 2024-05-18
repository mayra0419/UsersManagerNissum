package com.mayra0419.usersmanager.repository;


import com.mayra0419.usersmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User,String> {


}
