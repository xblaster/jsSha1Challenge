package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class BlockEntry  extends Model {
	
	
	public BlockEntry(String beginBlock, String endBlock) {
		super();
		this.beginBlock = beginBlock;
		this.endBlock = endBlock;
		this.status = 0;
	}


	public String beginBlock;
	public String endBlock;
	
	//0 available
	//1 given
	public int status;
	
	
	public static void generateNewBlock() {
		
	}
}
