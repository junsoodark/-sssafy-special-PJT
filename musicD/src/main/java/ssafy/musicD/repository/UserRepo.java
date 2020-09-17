package ssafy.musicD.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ssafy.musicD.models.User;

public interface UserRepo extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);

	Boolean existsByEmail(String email);
}
