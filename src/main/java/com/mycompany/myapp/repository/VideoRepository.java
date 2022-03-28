package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Video;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Video entity.
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query("select video from Video video where video.video.login = ?#{principal.username}")
    List<Video> findByVideoIsCurrentUser();

    default Optional<Video> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Video> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Video> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct video from Video video left join fetch video.video",
        countQuery = "select count(distinct video) from Video video"
    )
    Page<Video> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct video from Video video left join fetch video.video")
    List<Video> findAllWithToOneRelationships();

    @Query("select video from Video video left join fetch video.video where video.id =:id")
    Optional<Video> findOneWithToOneRelationships(@Param("id") Long id);
}
