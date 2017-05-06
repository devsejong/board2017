package net.chandol.study.board.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User getByUsername(String username);
    User getByEmail(String email);
}
