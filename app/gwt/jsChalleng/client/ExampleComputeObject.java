package gwt.jsChalleng.client;

import gwt.jsChalleng.client.sha1.Sha1;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;


public class ExampleComputeObject {

	HTMLPanel pane = new HTMLPanel("");
	
	public ExampleComputeObject() {
		RootPanel.get("result").add(pane);
		wordBuilder = new StringBuilder(" ");
	}
	
	public int count = 0;
	public int timedLaunched = 1;
	
	public StringBuilder wordBuilder;
	
	public void launch() {
		Timer t = new Timer() {
			
			@Override
			public void run() {
				String innerHTML = "<p>Word (size: "+getWord().length()+") in progress: <code>"+getWord()+"</code></p>";
				innerHTML += "<p>"+count/timedLaunched+" code sha1 par seconde</p>";
				
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
		if ((int)charToHandle>168) { //if arrive at last possible value
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
