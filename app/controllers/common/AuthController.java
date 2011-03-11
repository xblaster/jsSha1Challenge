package controllers.common;

import controllers.Application;
import controllers.Login;
import play.libs.OpenID;
import play.libs.OpenID.UserInfo;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Router;

public class AuthController extends Controller {
	
	@Before(unless={"Login.login", "Login.authenticate","Login.refuse","Login.debug"})
	static void checkAuthenticated() {
	    if(!session.contains("user")) {
	        Login.login();
	    } else {
	    	renderArgs.put("authenticated",true);
	    }
	    
	    if (Application.getAuthUser()==null) {
	    	Login.login();
	    }
	}
	
	@Before
	static void populate() {
		renderArgs.put("user", Application.getAuthUser());
		String url = Router.reverse("Challenge.index").url.replace("challenge/index", "");
		
		
		
		renderArgs.put("rootUrl", url);
	}
	 

}
