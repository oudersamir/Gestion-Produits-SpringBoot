package fsr.iao.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fsr.iao.entities.User;

public interface UserRepository  extends JpaRepository<User,Long> {

}
