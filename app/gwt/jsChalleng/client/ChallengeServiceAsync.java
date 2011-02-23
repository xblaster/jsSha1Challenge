package gwt.jsChalleng.client;

import models.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChallengeServiceAsync {
	void getUser(AsyncCallback<String> callback);
}
