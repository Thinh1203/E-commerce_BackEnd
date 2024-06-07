package com.ecommerce.WatchStore.Repository;

import com.ecommerce.WatchStore.Model.WatchImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WatchImageRepository extends JpaRepository<WatchImage, Long> {
    List<WatchImage> findByWatchId(Long watchId);
}
