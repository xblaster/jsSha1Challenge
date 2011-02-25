package gwt.jsChalleng.server;

import models.BlockEntry;
import models.User;
import controllers.Application;
import gwt.jsChalleng.client.ChallengeService;
import gwt.jsChalleng.client.word.BlockEntryDTO;
import play.modules.gwt2.GWT2Service;
import play.modules.gwt2.GWT2ServicePath;
import repository.BlockEntryRepository;

@GWT2ServicePath("/jsChalleng/challenge")
public class ChallengeServiceImpl extends GWT2Service implements ChallengeService {

	@Override
	public String getUser() {
		return Application.getAuthUser().email;
	}

	@Override
	public BlockEntryDTO getNewBlock() {
		return BlockEntryRepository.getNewBlock().toDTO();
	}

}
