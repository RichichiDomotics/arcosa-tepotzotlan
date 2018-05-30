package com.cbj.almacen.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by jolvera on 06/02/2015.
 */
public class MediUser extends org.springframework.security.core.userdetails.User {

    private String idEjecutivo;

    public MediUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String idEjecutivo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.setIdEjecutivo(idEjecutivo);
        this.idEjecutivo = idEjecutivo;
    }

    public String getIdEjecutivo() {
        return idEjecutivo;
    }

    public void setIdEjecutivo(String idEjecutivo) {
        this.idEjecutivo = idEjecutivo;
    }
}
