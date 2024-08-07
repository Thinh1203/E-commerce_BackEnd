package com.ecommerce.WatchStore.Repository;

import com.ecommerce.WatchStore.Model.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
    List<ReplyComment> findByCommentId(long commentId);
}
