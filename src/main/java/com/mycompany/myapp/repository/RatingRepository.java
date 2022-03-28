package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Rating;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Rating entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Query("select rating from Rating rating where rating.rating.login = ?#{principal.username}")
    List<Rating> findByRatingIsCurrentUser();
}
