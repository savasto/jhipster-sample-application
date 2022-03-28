package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Rating.
 */
@Entity
@Table(name = "rating")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private String rating;

    @Column(name = "date")
    private ZonedDateTime date;

    @ManyToOne
    @ManyToOne
    private User rating;

    @ManyToOne
    @ManyToOne
    @JsonIgnoreProperties(value = { "video" }, allowSetters = true)
    private Video rating;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Rating id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRating() {
        return this.rating;
    }

    public Rating rating(String rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ZonedDateTime getDate() {
        return this.date;
    }

    public Rating date(ZonedDateTime date) {
        this.setDate(date);
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public User getRating() {
        return this.rating;
    }

    public void setRating(User user) {
        this.rating = user;
    }

    public Rating rating(User user) {
        this.setRating(user);
        return this;
    }

    public Video getRating() {
        return this.rating;
    }

    public void setRating(Video video) {
        this.rating = video;
    }

    public Rating rating(Video video) {
        this.setRating(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rating)) {
            return false;
        }
        return id != null && id.equals(((Rating) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Rating{" +
            "id=" + getId() +
            ", rating='" + getRating() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
