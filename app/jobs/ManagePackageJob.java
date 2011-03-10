package jobs;

import models.BlockEntry;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;
import repository.BlockEntryRepository;

//@OnApplicationStart
@On("0 */5 * * * ?")
public class ManagePackageJob extends Job {
	 public void doJob() {
		 if (BlockEntry.count("status = ?",0)< 500) {
			 BlockEntryRepository.generateNewBlockEntries();
		 }
	 }
}
