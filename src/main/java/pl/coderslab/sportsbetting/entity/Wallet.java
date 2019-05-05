package pl.coderslab.sportsbetting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Class Wallet is representing informations about account status
 * can access all financial operations of assigned user
 */
@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;

    private Double status;

    @OneToMany(mappedBy = "wallet")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private List<Action> actions;

    public Wallet() {
    }

    public Wallet(User user) {
        this.user = user;
        this.status = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getStatus() {
        return status;
    }

    public void setStatus(Double status) {
        this.status = status;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
