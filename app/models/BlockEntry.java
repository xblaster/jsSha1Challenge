package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class BlockEntry  extends Model {
	String begin;
	String end;
	String status;
	public static void generateNewBlock() {
		
	}
}
