package gwt.jsChalleng.client;

import models.User;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Generated Entry Point
 * play-gwt2
 */
public class Jschalleng implements EntryPoint {
	
	
	private final ChallengeServiceAsync greetingService = GWT.create(ChallengeService.class);
	
	public void onModuleLoad() {
		
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "background", "transparent");
		Button b = new Button("Click me", new ClickHandler() {
			public void onClick(ClickEvent event) {
				greetingService.getUser(new AsyncCallback<String>() {
					
					
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						Window.alert("error");
					}
					@Override
					public void onSuccess(String result) {
						Window.alert(result);
					}
				});
			}
		});
		RootPanel.get("content").add(b);
		
		
	}
}
