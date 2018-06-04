package pl.coderslab.sportsbetting.entity;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User {
    private final pl.coderslab.sportsbetting.entity.User user;
    public CurrentUser(String username, String password, Collection<?
            extends GrantedAuthority> authorities,
                       pl.coderslab.sportsbetting.entity.User user) {
        super(username, password, authorities); this.user = user;
    }
    public pl.coderslab.sportsbetting.entity.User getUser() {return user;}
}
