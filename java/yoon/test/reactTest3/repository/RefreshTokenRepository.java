package yoon.test.reactTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.reactTest3.domain.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findRefreshTokenByMember_Email(String email);
    RefreshToken findRefreshTokenByToken(String token);
    boolean existsRefreshTokenByMember_Email(String email);
}
