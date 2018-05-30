package com.cbj.almacen.web;

import com.cbj.almacen.domain.MediUser;
import com.cbj.almacen.repository.UsuarioDao;
import com.cbj.almacen.service.UsuarioManager;
import com.cbj.almacen.service.impl.SecurityManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jolvera on 06/02/2015.
 */


@Controller
public class UserDetailController{

    private static final Logger logger = LoggerFactory
            .getLogger(UserDetailController.class);

    @Autowired
    private UsuarioManager usuarioManager;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        SecurityManagerImpl secManImp = new SecurityManagerImpl();
        MediUser mediUser = null;
        List authorities = new ArrayList();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        for(GrantedAuthority autority : user.getAuthorities()){
            authorities.add(new GrantedAuthorityImpl(autority.getAuthority()));
        }

        //Aqui se agregarian los valores adicionales llendo a DAO si se toma de BD
        com.cbj.almacen.domain.User usurec = this.usuarioManager.getUsuarioByUsername(user.getUsername());
        mediUser = new MediUser(username,"",true,false,false,false,authorities,"jolvera");

            return mediUser;
        }

}