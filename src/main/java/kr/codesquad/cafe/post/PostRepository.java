package kr.codesquad.cafe.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndIsDeleted(long id, boolean deleted);

    @EntityGraph("Post.withComments")
    List<Post> findAllByIsDeleted(boolean deleted, Pageable pageable);

    int countByIsDeleted(boolean deleted);

    @EntityGraph("Post.withComments")
    List<Post> findAllByUserId(long userId, Pageable pageable);

    int countByIsDeletedAndUserId(boolean deleted, long userId);
}
