package gwt.jsChalleng.server;

import gwt.jsChalleng.client.ChallengeService;
import gwt.jsChalleng.client.word.BlockEntryDTO;

import java.util.Date;

import models.BlockEntry;
import models.LogEntry;
import play.modules.gwt2.GWT2Service;
import play.modules.gwt2.GWT2ServicePath;
import repository.BlockEntryRepository;
import controllers.Application;

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

	@Override
	public void finishBlock(Long id) {
		BlockEntry block = BlockEntry.findById(id);
		
		//save the process on this block
		LogEntry log = new LogEntry();
		log.updateDate = new Date();
		log.user = Application.getAuthUser();
		log.blockId = id;
		log.save();
		
		block.delete();
	}

}
