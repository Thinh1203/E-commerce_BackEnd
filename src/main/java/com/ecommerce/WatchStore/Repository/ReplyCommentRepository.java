package com.ecommerce.WatchStore.Repository;

import com.ecommerce.WatchStore.Model.ReplyComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long> {
}
