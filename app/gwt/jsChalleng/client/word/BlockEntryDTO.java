package gwt.jsChalleng.client.word;

public class BlockEntryDTO {
	
	String begin;
	String end;
	
	public BlockEntryDTO(String begin, String end) {
		super();
		this.begin = begin;
		this.end = end;
	}

	public int size() {
		//empiric value (bad for the moment for a block)
		return 294813*3;
	}
	
	public String getBegin() {
		return begin;
	}
	public void setBegin(String begin) {
		this.begin = begin;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	
	public static BlockEntryDTO getMock() {
		return new BlockEntryDTO("   ", "~~~");
		//return new BlockEntryDTO("@   ", "_~~");
		//return new BlockEntryDTO("`   ", "~~~");
	}
	
}
