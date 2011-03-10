package jobs;

import java.util.List;

import models.BlockEntry;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;

@On("0 0 2 * * ?")
@OnApplicationStart
public class ManagePackageStatusJob extends Job{
	 public void doJob() {
		 List<BlockEntry> listBE = BlockEntry.find("status = ?", 1).fetch();
		 for (BlockEntry be : listBE) {
			 System.out.println("cleaning block "+be.id);
			 be.status = 0;
			 be.save();
		 }
	 }
}
