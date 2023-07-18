package yoon.test.reactTest3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yoon.test.reactTest3.domain.Posts;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {

    Posts findPostsByIdx(long idx);

    Optional<?> findByTitle(String title);

    Optional<?> findByMember_Name(String writer);

    List<Posts> findAllByTitleContaining(String title);

}
