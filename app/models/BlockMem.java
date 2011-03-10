package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

//to store memory
@Entity
public class BlockMem extends Model  {
	public String lastBlock;
	
}
