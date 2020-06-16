package fsr.iao.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fsr.iao.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	public Role findByRole(String role);

}
