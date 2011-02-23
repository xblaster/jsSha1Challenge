package controllers;

import models.User;
import controllers.common.AuthController;

public class Application extends AuthController {

    public static void index() {
        //Banks.index();
    	Challenge.index();
    }

    public static User getAuthUser() {
    	if (session.contains("userid")) {
    			return User.find("id = ?",Long.valueOf(session.get("userid"))).first();
    	} else {
    		return null;
    	}
    }
    
}