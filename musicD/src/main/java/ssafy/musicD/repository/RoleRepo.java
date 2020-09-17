package ssafy.musicD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.models.ERole;
import ssafy.musicD.models.Role;

public interface RoleRepo  extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);
}