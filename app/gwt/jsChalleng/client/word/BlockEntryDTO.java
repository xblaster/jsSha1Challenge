package gwt.jsChalleng.client.word;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BlockEntryDTO implements IsSerializable {
	
	public String beginBlock;
	public String endBlock;
	public Long id;
	
	public BlockEntryDTO() {
		this("","",0l);
	}
	
	public BlockEntryDTO(String begin, String end, Long id) {
		super();
		this.beginBlock = begin;
		this.endBlock = end;
		this.id = id;
	}

	public int size() {
		//empiric value (bad for the moment for a block)
		//return 294813*3;
		return 294813;
	}
	
	public String getBegin() {
		return beginBlock;
	}
	public void setBegin(String begin) {
		this.beginBlock = begin;
	}
	public String getEnd() {
		return endBlock;
	}
	public void setEnd(String end) {
		this.endBlock = end;
	}
	
	
	public static BlockEntryDTO getMock() {
		return new BlockEntryDTO("   ", "~~~", 0l);
		//return new BlockEntryDTO("@   ", "_~~");
		//return new BlockEntryDTO("`   ", "~~~");
	}
	
}
