package fer.ftb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fer.ftb.entities.User;

/** 
 * 
 * @author Fernando Torres Bautista
 *
 */

// User repository interface

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

public User findOneByUsername(String username);
}
