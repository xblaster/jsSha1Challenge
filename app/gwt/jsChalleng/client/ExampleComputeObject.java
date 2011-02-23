package gwt.jsChalleng.client;

import gwt.jsChalleng.client.sha1.Sha1;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ExampleComputeObject {

	HTMLPanel pane = new HTMLPanel("");
	
	public ExampleComputeObject() {
		RootPanel.get("result").add(pane);
	}
	
	public int count = 0;
	public int timedLaunched = 1;
	
	public void launch() {
		Timer t = new Timer() {
			
			@Override
			public void run() {
				pane.getElement().setInnerHTML(count/timedLaunched+" code sha1 par seconde");
				timedLaunched++;
			}
		};
		
		t.scheduleRepeating(1000);
		
		work();
		
	}
	
	public void work() {
		Timer tWork = new Timer() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					Sha1.calculate("lolrtjertjeriojeiotjeroiter478");
					count++;
				}
				work();
			}
		};
		
		tWork.schedule(1);
		
		/*Timer tWork2 = new Timer() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					Sha1.calculate("lolrtjertjeriojeiotjeroiter478");
					count++;
				}
			}
		};
		
		tWork2.schedule(1);*/
	}

}
