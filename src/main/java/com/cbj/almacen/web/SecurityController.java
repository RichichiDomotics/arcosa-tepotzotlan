package com.cbj.almacen.web;

import com.cbj.almacen.domain.User;
import com.cbj.almacen.service.SecurityUserManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

/**
 * Created by jolvera on 11/05/2014.
 */
@Controller
public class SecurityController {

    private static final Logger logger = LoggerFactory
            .getLogger(EntradasController.class);

    @Autowired
    private SecurityUserManager securityUserManager;

    @RequestMapping(value = "/serviceLogin", method = RequestMethod.GET)
    public String loginws(Locale locale,@RequestParam("jsonObj") String jsonObjStr) {
        JSONObject reader = new JSONObject(jsonObjStr);
        User user = securityUserManager.getLoginSession(reader);
        if (user!=null && user.isEnabled()) {
            return "loginok";
        }
        return "loginerror";
    }
}
