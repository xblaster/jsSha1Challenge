package jobs;

import models.BlockEntry;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;

//@OnApplicationStart
@On("*/5 * * * * ?")
public class ManagePackageJob extends Job {
	 public void doJob() {
		 if (BlockEntry.count()< 100) {
			 BlockEntry.generateNewBlock();
		 }
	 }
}
