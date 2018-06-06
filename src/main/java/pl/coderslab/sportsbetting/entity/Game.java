package pl.coderslab.sportsbetting.entity;

import javax.persistence.*;

import org.joda.time.LocalDateTime;

import java.util.Date;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String country;

    private String place;

//    @OneToMany(mappedBy = "game")
//    private List<Horse> horses;
    @OneToMany(mappedBy = "game")
    private List<Result> results;

    private Date startingAt;

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

//    public List<Horse> getHorses() {
//        return horses;
//    }
//
//    public void setHorses(List<Horse> horses) {
//        this.horses = horses;
//    }


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
}
