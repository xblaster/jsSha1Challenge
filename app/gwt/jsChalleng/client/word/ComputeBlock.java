package gwt.jsChalleng.client.word;

import gwt.jsChalleng.client.sha1.Sha1;
import gwt.jsChalleng.shared.WordGenerator;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ComputeBlock {

	HTMLPanel pane = new HTMLPanel("");
	protected int blockAchieved = 0;

	private WordGenerator wordGenerator = new WordGenerator();
	
	public ComputeBlock() {
		RootPanel.get("result").add(pane);
		//wordBuilder = new StringBuilder(" ");
		
		Timer t = new Timer() {
			

			@Override
			public void run() {
				String innerHTML = "<p>Word (size: "+getWord().length()+") in progress: <code>"+getWord()+"</code></p>";
				innerHTML += "<p>"+count/timedLaunched+" code sha1 par seconde. total "+count*100/block.size()+"% "+count+"</p>";
				innerHTML += "<h1>Block achieved: <b>"+blockAchieved+"</b></h1>";
				
				pane.getElement().setInnerHTML(innerHTML);
				timedLaunched++;
			}
		};
		
		t.scheduleRepeating(1000);
		
		onBlockComplete();
	}
	
	public int count = 0;
	public int timedLaunched = 1;
	
	private BlockEntryDTO block;
	
	public void runBlock(BlockEntryDTO blockEntryDTO) {
		
		this.block = blockEntryDTO;
		
		//init war
		count = 0;
		timedLaunched = 1;
		
		work();
	}
	
	public String getWord() {
		return wordGenerator.getWord();
	}
	
	public void work() {
		Timer tWork = new Timer() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					
					String word = wordGenerator.nextWord().toString();
					Sha1.calculate(word);
					
					if (word.equals(block.getEnd())) {
						onBlockComplete();
						return;
					}
					System.out.println(word+" "+block.getEnd());
					System.out.println(word);
					count++;
				}
				work();
			}
		};
		
		tWork.schedule(1);
	}
	
	protected void onBlockComplete() {
		//dummy for the moment
		runBlock(BlockEntryDTO.getMock());
		blockAchieved++;
	}



}
