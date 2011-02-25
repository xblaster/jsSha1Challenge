package gwt.jsChalleng.client;

import gwt.jsChalleng.client.word.BlockEntryDTO;
import models.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ChallengeServiceAsync {
	void getUser(AsyncCallback<String> callback);

	void getNewBlock(AsyncCallback<BlockEntryDTO> callback);
}
