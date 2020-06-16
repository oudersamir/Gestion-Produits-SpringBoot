package fsr.iao.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name="users")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class User implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotNull
	@Size(min=5,max=10)
	private String username;
	@NotNull
	@Size(min=5,max=10)
	private String password;
	private boolean actived;
	@ManyToMany
	@JoinTable(name="USERS_ROLES")
	private Collection<Role> roles=new ArrayList<Role>();
}
