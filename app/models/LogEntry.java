package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class LogEntry extends Model{
	public Date updateDate;
	public Long blockId;
	
	@OneToOne
	public User user;
}
