package repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import models.LogEntry;
import models.User;
import util.DateUtil;
import controllers.Application;

public class LogEntryRepository {
	public static List<Entry<Date, Long>> getStatFor(User u) {
		List<Entry<Date,Long>> result = new LinkedList<Entry<Date,Long>>();
		
		
		//System.out.println(Application.getAuthUser());
		LogEntry first = LogEntry.find("user = ? order by updateDate", Application.getAuthUser()).first();
		//System.out.println(first.updateDate);
		
		LogEntry last = LogEntry.find("user = ? order by updateDate DESC", Application.getAuthUser()).first();
		//System.out.println(last.updateDate);
		
		Date d = DateUtil.getOnlyDatePart(first.updateDate);
		while(d.compareTo(last.updateDate)==-1) {
			//System.out.println(d);
			long count = LogEntry.count("user = ? and updateDate > ? and updateDate < ?", Application.getAuthUser(), d, DateUtil.getTommorow(d));
			//System.out.println(count);
			d = DateUtil.getTommorow(d);
			
			result.add(new Entry<Date, Long>() {
				
				@Override
				public Long setValue(Long value) {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Long getValue() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public Date getKey() {
					// TODO Auto-generated method stub
					return null;
				}
			});
		}
		
		//String debug = ""+LogEntry.count("user = ? ", Application.getAuthUser());
		return result;
	}
}
