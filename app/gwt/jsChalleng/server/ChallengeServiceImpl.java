package gwt.jsChalleng.server;

import models.User;
import controllers.Application;
import gwt.jsChalleng.client.ChallengeService;
import play.modules.gwt2.GWT2Service;
import play.modules.gwt2.GWT2ServicePath;

@GWT2ServicePath("/jsChalleng/challenge")
public class ChallengeServiceImpl extends GWT2Service implements ChallengeService {

	@Override
	public String getUser() {
		return Application.getAuthUser().email;
	}

}
