package pl.coderslab.sportsbetting.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.joda.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Class Game is representing the races
 */
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String place;

    @OneToMany(mappedBy = "game")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Result> results;

    private Date startingAt;

    private Date finishingAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Date getStartingAt() {
        return startingAt;
    }

    public void setStartingAt(Date startingAt) {
        this.startingAt = startingAt;
    }

    public Date getFinishingAt() {
        return finishingAt;
    }

    public void setFinishingAt(Date finishingAt) {
        this.finishingAt = finishingAt;
    }
}
