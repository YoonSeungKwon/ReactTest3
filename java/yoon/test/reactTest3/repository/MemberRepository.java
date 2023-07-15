package yoon.test.reactTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.reactTest3.domain.Members;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {

    Optional<?> findByEmail(String email);

    Members findMembersByEmail(String email);

}
