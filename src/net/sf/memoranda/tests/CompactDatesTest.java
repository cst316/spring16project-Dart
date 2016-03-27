package net.sf.memoranda.tests;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.TaskListImpl;
import net.sf.memoranda.date.CalendarDate;

public class CompactDatesTest {
	
	TaskList tl;
	
	@Before
	public void init(){
		String title = "Dummy Project";
		//Project start date will be today, end date will be 20 days from now.
		Calendar today = Calendar.getInstance();
		Calendar twentyDaysFromNow = Calendar.getInstance();
		twentyDaysFromNow.add(Calendar.DAY_OF_MONTH, 20);
		
		//Need a project to create a tasklist; this initial setup is necessary
		CalendarDate startD = new CalendarDate(today);
		CalendarDate endD = new CalendarDate(twentyDaysFromNow);
		Project prj = ProjectManager.createProject(title, startD, endD);
		tl = new TaskListImpl(prj);
	}

	@Test
	public void test() {
		//Supertask start and end dates:
		int superLength = 10;
		Calendar superStartD = Calendar.getInstance();
		superStartD.add(Calendar.DAY_OF_MONTH, 4);//TEMP TODO PLEASE DELETE THIS
		Calendar superEndD = Calendar.getInstance();
		superEndD.add(Calendar.DAY_OF_MONTH, superLength);
		CalendarDate super_start = new CalendarDate(superStartD);
		CalendarDate super_end = new CalendarDate(superEndD);
		
		//Subtask 1 start and end dates:
		Calendar sub1StartD = Calendar.getInstance();
		sub1StartD.add(Calendar.DAY_OF_MONTH, 2);
		Calendar sub1EndD = Calendar.getInstance();
		sub1EndD.add(Calendar.DAY_OF_MONTH, 8);
		CalendarDate sub1_start = new CalendarDate(sub1StartD);
		CalendarDate sub1_end = new CalendarDate(sub1EndD);
		
		//Subtask 2 start and end dates:
		Calendar sub2StartD = Calendar.getInstance();
		sub2StartD.add(Calendar.DAY_OF_MONTH, 3);
		Calendar sub2EndD = Calendar.getInstance();
		sub2EndD.add(Calendar.DAY_OF_MONTH, 7);
		CalendarDate sub2_start = new CalendarDate(sub2StartD);
		CalendarDate sub2_end = new CalendarDate(sub2EndD);
				
		//Subtask 3 start and end dates:
		Calendar sub3StartD = Calendar.getInstance();
		sub3StartD.add(Calendar.DAY_OF_MONTH, 4);
		Calendar sub3EndD = Calendar.getInstance();
		sub3EndD.add(Calendar.DAY_OF_MONTH, 6);
		CalendarDate sub3_start = new CalendarDate(sub3StartD);
		CalendarDate sub3_end = new CalendarDate(sub3EndD);
		
		//Create the tasks:
		Task superTask = tl.createTask(super_start, super_end, "super", 1, 80, "super desc", null);
		Task sub1 = tl.createTask(sub1_start, sub1_end, "sub1", 1, 80, "sub1 desc", superTask.getID());
		Task sub2 = tl.createTask(sub2_start, sub2_end, "sub2", 1, 80, "sub2 desc", superTask.getID());
		Task sub3 = tl.createTask(sub3_start, sub3_end, "sub3", 1, 80, "sub3 desc", superTask.getID());
		
		
	}

}
