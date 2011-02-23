package gwt.jsChalleng.client;

import models.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("challenge")
public interface ChallengeService extends RemoteService {
	String getUser();
}
