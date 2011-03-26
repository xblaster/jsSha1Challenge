package controllers;

import java.util.Date;

import models.LogEntry;
import models.User;
import play.libs.OpenID;
import play.libs.OpenID.UserInfo;
import repository.BlockEntryRepository;
import util.DateUtil;
import controllers.common.AuthController;

public class Login extends AuthController {

	public static void index() {
	    Application.index();
	}
	     
	public static void login() {
		renderArgs.put("total", BlockEntryRepository.getTotalBlock());
	    render();
	}
	
	public static void refuse() {
	    render();
	}
	    
	public static void authenticate(String authurl) {
		
		
	    if(OpenID.isAuthenticationResponse()) {
	        UserInfo verifiedUser = OpenID.getVerifiedID();
	        if(verifiedUser == null) {
	            flash.put("error", "Oops. Authentication has failed");
	            login();
	        } 
	        
	        storeInDb(verifiedUser);
	        index();
	    } else {
	    	if (authurl.equals("anonymous")) { //if request anonymous
				setAsAnonymous();
				index();
				return;
			}
	    	
	    	 if(!OpenID.id(authurl).
	    			 	required("email","http://axschema.org/contact/email").
	    			 	required("firstname","http://axschema.org/namePerson/first").
	    			 	required("lastname","http://axschema.org/namePerson/lastname")
	    			 	.verify()
	    			 	) { 
	    	               flash.put("error", "Oops. Cannot contact google"); 
	    	               index(); 
	    	         } 
	    }
	    
	}

	private static void setAsAnonymous() {
		UserInfo anonymousUser = new UserInfo();
		anonymousUser.id= "noId";
		anonymousUser.extensions.put("email", "none");
		anonymousUser.extensions.put("firstname", "anonymous");
		anonymousUser.extensions.put("lastname", " ");
		
		storeInDb(anonymousUser);
	}
	
	private static void storeInDb(UserInfo verifiedUser) {
		session.put("user", verifiedUser.id);
		
		User u = User.find("urlid = ?",verifiedUser.id).first();
		if (u ==null) {
			u = new User();
			u.urlid = verifiedUser.id;
			u.email = verifiedUser.extensions.get("email");
			u.firstname = verifiedUser.extensions.get("firstname");
			u.lastname = verifiedUser.extensions.get("lastname");
			u.save();
			
			session.put("userid", u.id);
			
			flash.success("account for %s has been created",u.firstname);
			Application.index();
		}
		session.put("userid", u.id);
	}
	
	public static void disconnect() {
		session.remove("userid");
		session.remove("user");
		
		flash.put("success","You are now disconnected");
		
		Application.index();
	}
	
	public static void debug() {
		//LogEntry.count(
		LogEntry first = LogEntry.find("user = ? order by updateDate", Application.getAuthUser()).first();
		System.out.println(first.updateDate);
		
		LogEntry last = LogEntry.find("user = ? order by updateDate DESC", Application.getAuthUser()).first();
		System.out.println(last.updateDate);
		
		Date d = DateUtil.getOnlyDatePart(first.updateDate);
		while(d.compareTo(last.updateDate)==-1) {
			System.out.println(d);
			long count = LogEntry.count("user = ? and updateDate < ? and updateDate > ?", Application.getAuthUser(), d, DateUtil.getTommorow(d));
			System.out.println(count);
			d = DateUtil.getTommorow(d);
		}
		
		String debug = ""+LogEntry.count("user = ? ", Application.getAuthUser());
		render(debug);
	}
	
	

}
