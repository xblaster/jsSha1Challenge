package controllers;

import models.User;
import play.cache.Cache;
import controllers.common.AuthController;

public class Application extends AuthController {

    public static void index() {
        //Banks.index();
    	Challenge.index();
    }

    public static User getAuthUser() {
    	if (session.contains("userid")) {
    			return getCachedUserForId(Long.valueOf(session.get("userid")));
    			
    	} else {
    		return null;
    	}
    }

	private static User getCachedUserForId(Long id) {
		User user = Cache.get("User"+id, User.class);
		if (user==null) {
			User retrievedUser = User.findById(id);;
			Cache.set("User"+id, retrievedUser, "30mn");
			return retrievedUser;
		}
		return user;
	}
    
}