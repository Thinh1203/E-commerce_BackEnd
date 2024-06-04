package com.ecommerce.WatchStore.Repository;

import com.ecommerce.WatchStore.Model.Watch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Long> {
}
