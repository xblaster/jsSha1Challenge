package gwt.jsChalleng.client.word;

import gwt.jsChalleng.client.ChallengeService;
import gwt.jsChalleng.client.ChallengeServiceAsync;
import gwt.jsChalleng.client.sha1.Sha1;
import gwt.jsChalleng.shared.WordGenerator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ComputeBlock {

	HTMLPanel pane = new HTMLPanel("");
	protected int blockAchieved = 0;

	private final ChallengeServiceAsync challengeService = GWT.create(ChallengeService.class);
	
	private WordGenerator wordGenerator = new WordGenerator();
	
	public ComputeBlock() {
		RootPanel.get("result").add(pane);
		//wordBuilder = new StringBuilder(" ");
		
		Timer t = new Timer() {
			

			@Override
			public void run() {
				String innerHTML = "<p>Word (size "+getWord().length()+") in progress: <code>"+getWord()+"</code></p>";
				innerHTML += "<p>"+count/timedLaunched+" code sha1 par seconde.</p>";// total "+count*100/block.size()+"% "+count+"</p>";
				innerHTML += "<div class=\"progress-container\"><div style=\"width: "+count*100/block.size()+"%\"></div></div>";
				innerHTML += "<h1>Block achieved: <b>"+blockAchieved+"</b></h1>";
				
				pane.getElement().setInnerHTML(innerHTML);
				timedLaunched++;
			}
		};
		
		t.scheduleRepeating(1000);
		
		getNewBlock();
	}
	
	public int count = 0;
	public int timedLaunched = 1;
	
	private BlockEntryDTO block;
	
	public void runBlock(BlockEntryDTO blockEntryDTO) {
		
		this.block = blockEntryDTO;
		
		//init war
		count = 0;
		timedLaunched = 1;
		
		wordGenerator.setWord(block.getBegin());
		
		work();
		System.out.println(block.getBegin()+" "+block.getEnd());

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
					count++;
				}
				work();
			}
		};
		
		tWork.schedule(1);
	}
	
	protected void onBlockComplete() {
		
		challengeService.finishBlock(block.id,new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				//doesn't matter for the moment :\
			}

			@Override
			public void onSuccess(Void result) {
				//nothing to do
			}
		});
		blockAchieved++;
		//ask for a new block in parallel
		getNewBlock();
	}
	
	protected void getNewBlock() {
		challengeService.getNewBlock(new AsyncCallback<BlockEntryDTO>() {
			
			@Override
			public void onSuccess(BlockEntryDTO result) {
				runBlock(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.toString());
			}
		});
		
	}



}
