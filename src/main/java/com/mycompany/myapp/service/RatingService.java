package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Rating;
import com.mycompany.myapp.repository.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Rating}.
 */
@Service
@Transactional
public class RatingService {

    private final Logger log = LoggerFactory.getLogger(RatingService.class);

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Save a rating.
     *
     * @param rating the entity to save.
     * @return the persisted entity.
     */
    public Rating save(Rating rating) {
        log.debug("Request to save Rating : {}", rating);
        return ratingRepository.save(rating);
    }

    /**
     * Partially update a rating.
     *
     * @param rating the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Rating> partialUpdate(Rating rating) {
        log.debug("Request to partially update Rating : {}", rating);

        return ratingRepository
            .findById(rating.getId())
            .map(existingRating -> {
                if (rating.getRating() != null) {
                    existingRating.setRating(rating.getRating());
                }
                if (rating.getDate() != null) {
                    existingRating.setDate(rating.getDate());
                }

                return existingRating;
            })
            .map(ratingRepository::save);
    }

    /**
     * Get all the ratings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Rating> findAll() {
        log.debug("Request to get all Ratings");
        return ratingRepository.findAll();
    }

    /**
     * Get one rating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Rating> findOne(Long id) {
        log.debug("Request to get Rating : {}", id);
        return ratingRepository.findById(id);
    }

    /**
     * Delete the rating by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rating : {}", id);
        ratingRepository.deleteById(id);
    }
}
