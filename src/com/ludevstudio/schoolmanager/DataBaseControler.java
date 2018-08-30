package com.ludevstudio.schoolmanager;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.I2D;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;



public class DataBaseControler {
	final String DATABASE_PATH = System.getenv("APPDATA")+"/LuDev Studio/School Manager/";
	final String DATABASE_NAME = "save.db";
	
	
	Connection connection;
	
	
	public DataBaseControler()  {
		final File DATABASE_DIR = new File(DATABASE_PATH);
		if(!DATABASE_DIR.exists()) 
			DATABASE_DIR.mkdirs();
	
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_PATH + DATABASE_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void createNewSchedule(String name, String comment, int houers, Boolean zeroHouer, Boolean weekend) {
		String zeroHouerStr = zeroHouer.toString();
		String weekendStr = weekend.toString();
		
		final String TABLE_NAME = "Schedules";
		final String COL_NAME = "Name";
		final String COL_COMMENT = "Comment";
		final String COL_HOUERS = "Lessons";
		final String COL_ZEROHOUER = "Zerolesson";
		final String COL_WEEKEND = "Weekend";
		
		
			
			try {
				Statement stat = connection.createStatement();
			
			// Create schedules table if not exists
			stat.execute("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
				+ COL_NAME +" VARCHAR (255),"
				+ COL_COMMENT+" VARCHAR (255),"
				+ COL_HOUERS+" INT,"
				+ COL_ZEROHOUER +" VARCHAR(5),"
				+ COL_WEEKEND + " VARCHAR(5));");
			
			
			
				// Insert new schedule infos into table
					stat.execute("INSERT INTO "+TABLE_NAME+" ("+COL_NAME+", "+COL_COMMENT+", "+COL_HOUERS+", "+COL_ZEROHOUER+", "+COL_WEEKEND
					+ ") VALUES('"+name+"', '"+comment+"', "+houers+", "+zeroHouerStr+", "+weekendStr+");");
			
					
				// create table for the new schedule
					name = name.replace(" ", "_");
				
					
					
					stat.executeUpdate("CREATE TABLE IF NOT EXISTS Schedule_"+name+" " +
							"('Lesson' integer, '1' integer, '2' integer, '3' integer, '4' integer, '5' integer, '6' integer, '7' integer);");
					
					
					
					// insert lesson numbers
					
					
					
					for(int i=0; i<=houers; i++) {
						stat.executeUpdate("INSERT INTO Schedule_"+name+" (Lesson) VALUES("+i+");");
					}
					
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}
	
	public List<String> getScheduleList() {
		ArrayList<String> list =new ArrayList<String>();
				
		
		try {
			Statement stat = connection.createStatement();
			
			ResultSet rs = stat.executeQuery("SELECT Name FROM Schedules;");
			while(rs.next())
				list.add(rs.getString(1));
		
		} catch (SQLException e) {
			
		}
		
		return list;
	}
	
	
	
	public ArrayList<String> loadScheduleInfos(String name) {
		
		final String TABLE_NAME = "Schedules";
		ArrayList<String> infos =new ArrayList<>();
		
		try {
			Statement stat = connection.createStatement();
			
			ResultSet rs = stat.executeQuery("SELECT * FROM "+TABLE_NAME+" WHERE Name='"+name+"';");
			while(rs.next()) {
				for(int i=0; i<5; i++) {
					infos.add(rs.getString(i+1));
				}
				
			}
			
		
		} catch (SQLException e) {
			return infos;
		}
		return infos;
	}
	
	public ArrayList<String[]> loadScheduleSubjects(String scheduleName) {
		ArrayList<String[]> subjects = new ArrayList<>();
		scheduleName = scheduleName.replace(" ", "_");
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Schedule_"+scheduleName+"(Lesson, '1', '2', '3', '4', '5', '6', '7');");
			ResultSet rs = stat.executeQuery("SELECT * FROM Schedule_"+scheduleName+";");
			
			
			while(rs.next()) {
				subjects.add(new String[] {rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	
	
	public void saveSchedule(String schedule, String ID, String day,String lesson) {
		schedule = schedule.replace(" ", "_");
		final String TABLE_NAME = "Schedule_"+schedule;
		
		
		try {
			
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
					+ "'Lesson', '1', '2', '3', '4', '5', '6', '7');");
			
			stat.executeUpdate("UPDATE "+TABLE_NAME+" SET '"+day+"'='"+ID+"' WHERE lesson = "+lesson+";"); // HIER!!!
				
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public ArrayList<String> getSubjectList() {
		ArrayList<String> subs =new ArrayList<>();
		final String TABLE_NAME = "Subjects";
		final String COL_ID = "ID";
		final String COL_NAME = "Name";
		final String COL_TEACHER = "Teacher";
		final String COL_ROOM = "Room";
		final String COL_COLOR = "Color";
		
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"("+COL_NAME+", "+COL_NAME+", "+COL_TEACHER+", "
					+COL_ROOM+", "+COL_COLOR + ");");
			ResultSet rs = stat.executeQuery("SELECT "+COL_ID+" FROM "+TABLE_NAME+";");
			
			while(rs.next()) {
				subs.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		return subs;
		}
	
		return subs;
	}
	
	
	
	
	public ArrayList<String> loadSubject(String ID) {
		ArrayList<String> subject = new ArrayList<>();
		
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Subjects ('ID', 'Name', 'Teacher', 'Room', 'Color');");
			ResultSet rs = stat.executeQuery("SELECT * FROM Subjects WHERE ID = '"+ID+"';");
			
			while(rs.next()) {
				subject.add(rs.getString(1));
				subject.add(rs.getString(2));
				subject.add(rs.getString(3));
				subject.add(rs.getString(4));
				subject.add(rs.getString(5));
			}	
			
			
			
			stat.close();
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subject;
	}
	
	public Boolean isSubjectExisting(String name) {
ArrayList<String> subject = new ArrayList<>();
		
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Subjects ('Name', 'Teacher', 'Room', 'Color');");
			ResultSet rs = stat.executeQuery("SELECT * FROM Subjects WHERE Name = '"+name+"';");
			
			while(rs.next()) {
				subject.add(rs.getString(1));
				
			}	
			
			stat.close();
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(subject.size()>0) {
			return true; 
		}
		
		
		return false;
	}
	
	public void createNewSubject(String name, String teacher, String room, String color) {
		final String TABLE_NAME = "Subjects";
		final String COL_ID = "ID";
		final String COL_NAME = "Name";
		final String COL_TEACHER = "Teacher";
		final String COL_ROOM = "Room";
		final String COL_COLOR = "Color";
	
			
			try {
				Statement stat = connection.createStatement();
			
			// Create subjects table if not exists
			stat.execute("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("
				+ COL_ID +", "
				+ COL_NAME +", "
				+ COL_TEACHER+", "
				+ COL_ROOM+", "
				+ COL_COLOR + ");");
			
				// Generate ID
				final long ID = System.currentTimeMillis();
			
				// Insert new subject infos into table
					stat.execute("INSERT INTO "+TABLE_NAME+" ('"+COL_ID+"', '"+COL_NAME+"', '"+COL_TEACHER+"', '"+COL_ROOM+"', '"+COL_COLOR+"' ) "
							+ "VALUES('"+ID+"', '"+name+"', '"+teacher+"', '"+room+"', '"+color+"');");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				}
	
	
	public String getSubjectID(String name, String teacher, String room, String color) {
		final String TABLE_NAME = "Subjects";
		final String COL_ID = "ID";
		final String COL_NAME = "Name";
		final String COL_TEACHER = "Teacher";
		final String COL_ROOM = "Room";
		final String COL_COLOR = "Color";
		
		
		String ID = "";
		
		try {
			Statement stat = connection.createStatement();
			
			
			ResultSet rs = stat.executeQuery("SELECT * FROM "+TABLE_NAME
					+ " WHERE "
					+ COL_NAME+" = '"+name+"' AND "
					+ COL_TEACHER+" = '"+teacher+"' AND "
					+ COL_ROOM+" = '"+room+"' AND "			
					+ COL_COLOR+" = '"+color+"';");
					
		while(rs.next()) {
			ID = rs.getString(1);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ID;
	}
	
	public ArrayList<String[]> loadSubjects() {
		ArrayList<String[]> subjects = new ArrayList<>();
		
		try {
			Statement stat = connection.createStatement();
			stat.executeUpdate("CREATE TABLE IF NOT EXISTS Subjects ('ID', 'Name', 'Teacher', 'Room', 'Color');");
			ResultSet rs = stat.executeQuery("SELECT * FROM Subjects;");
			
			while(rs.next()) {
				String[] sub = new String[] {
					rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4),
					rs.getString(5)
				};
				subjects.add(sub);
			}	
			
			
			
			stat.close();
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return subjects;
	}
	
	
}
