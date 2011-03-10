package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class StatEntry extends Model {
	public Date statForDate;
	public Long totalBlock;
	
	@OneToOne
	public User user;
}
