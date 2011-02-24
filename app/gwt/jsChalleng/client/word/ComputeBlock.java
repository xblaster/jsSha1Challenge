package gwt.jsChalleng.client.word;

import gwt.jsChalleng.client.sha1.Sha1;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ComputeBlock {

	HTMLPanel pane = new HTMLPanel("");
	
	public ComputeBlock() {
		RootPanel.get("result").add(pane);
		wordBuilder = new StringBuilder(" ");
	}
	
	public int count = 0;
	public int timedLaunched = 1;
	
	public StringBuilder wordBuilder;
	private BlockEntryDTO block;
	
	public void runBlock(BlockEntryDTO blockEntryDTO) {
		
		this.block = blockEntryDTO;
		
		//init word
		wordBuilder = new StringBuilder(blockEntryDTO.getBegin());
		
		Timer t = new Timer() {
			
			@Override
			public void run() {
				String innerHTML = "<p>Word (size: "+getWord().length()+") in progress: <code>"+getWord()+"</code></p>";
				innerHTML += "<p>"+count/timedLaunched+" code sha1 par seconde. total "+count+"</p>";
				
				pane.getElement().setInnerHTML(innerHTML);
				timedLaunched++;
			}
		};
		
		t.scheduleRepeating(1000);
		
		work();
		
	}
	
	public String getWord() {
		return wordBuilder.toString();
	}
	
	public void work() {
		Timer tWork = new Timer() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					
					String word = nextWord().toString();
					Sha1.calculate(word);
					
					if (word.equals(block.getEnd())) {
						System.out.println("all done");
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
	
	public StringBuilder nextWord() {
		return nextWord(wordBuilder.length()-1);
	}
	
	public StringBuilder nextWord(int position) {
		
		int charToHandle = wordBuilder.charAt(position);
		//if ((int)charToHandle>168) { //if arrive at last possible value
		if ((int)charToHandle>126) { //if arrive at last possible value
			if (position!=0) {
				return nextWord(position-1);
			} else {
				return generateBiggerWord();
			}
		}
		
		wordBuilder.setCharAt(position, (char)(charToHandle+1));
		for (int i = position+1; i < wordBuilder.length(); i++) {
			//reset char after
			wordBuilder.setCharAt(i, (char)32);
		}
		
		return wordBuilder;
	}

	private StringBuilder generateBiggerWord() {
		int newLen = wordBuilder.length()+1;
		
		wordBuilder = new StringBuilder();
		wordBuilder.setLength(newLen);
		
		for (int i = 0; i < newLen; i++) {
			wordBuilder.setCharAt(i, (char)32);
		}
		return wordBuilder;
	}

}
