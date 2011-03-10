package jobs;

import models.BlockEntry;
import play.jobs.Every;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;
import repository.BlockEntryRepository;

@OnApplicationStart
@Every("1h")
public class ManagePackageJob extends Job {
	 public void doJob() {
		 if (BlockEntry.count("status = ?",0)< 500) {
			 BlockEntryRepository.generateNewBlockEntries();
		 }
	 }
}
