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

	/**
	 * This method tests the situation where the supertask has one or more subtasks
	 * whose start date is earlier than the supertask's start date and whose end date
	 * is later than the supertask's end date.  Thus, the supertask should "grow" its
	 * date range in both directions, hence why this method is named testGrowBoth.
	 */
	@Test
	public void testGrowBoth() {
		//Supertask start and end dates:
		Calendar superStartD = Calendar.getInstance();
		superStartD.add(Calendar.DAY_OF_MONTH, 4);
		Calendar superEndD = Calendar.getInstance();
		superEndD.add(Calendar.DAY_OF_MONTH, 6);
		CalendarDate super_start = new CalendarDate(superStartD);
		CalendarDate super_end = new CalendarDate(superEndD);
		
		//Subtask 1 start and end dates (subtask 1 has the largest range):
		Calendar sub1StartD = Calendar.getInstance();
		sub1StartD.add(Calendar.DAY_OF_MONTH, 1);
		Calendar sub1EndD = Calendar.getInstance();
		sub1EndD.add(Calendar.DAY_OF_MONTH, 10);
		CalendarDate sub1_start = new CalendarDate(sub1StartD);
		CalendarDate sub1_end = new CalendarDate(sub1EndD);
		
		//Subtask 2 start and end dates:
		Calendar sub2StartD = Calendar.getInstance();
		sub2StartD.add(Calendar.DAY_OF_MONTH, 2);
		Calendar sub2EndD = Calendar.getInstance();
		sub2EndD.add(Calendar.DAY_OF_MONTH, 9);
		CalendarDate sub2_start = new CalendarDate(sub2StartD);
		CalendarDate sub2_end = new CalendarDate(sub2EndD);
				
		//Subtask 3 start and end dates:
		Calendar sub3StartD = Calendar.getInstance();
		sub3StartD.add(Calendar.DAY_OF_MONTH, 3);
		Calendar sub3EndD = Calendar.getInstance();
		sub3EndD.add(Calendar.DAY_OF_MONTH, 8);
		CalendarDate sub3_start = new CalendarDate(sub3StartD);
		CalendarDate sub3_end = new CalendarDate(sub3EndD);
		
		//Create the tasks:
		Task superTask = tl.createTask(super_start, super_end, "super", 1, 80, "super desc", null);
		Task sub1 = tl.createTask(sub1_start, sub1_end, "sub1", 1, 80, "sub1 desc", superTask.getID());
		Task sub2 = tl.createTask(sub2_start, sub2_end, "sub2", 1, 80, "sub2 desc", superTask.getID());
		Task sub3 = tl.createTask(sub3_start, sub3_end, "sub3", 1, 80, "sub3 desc", superTask.getID());
		
		//Test the date compaction algorithm:
		superTask.setStartDate(tl.getEarliestStartDateFromSubTasks(superTask));
        superTask.setEndDate(tl.getLatestEndDateFromSubTasks(superTask));
        
        //Get the resultant dates:
        CalendarDate resultStart = superTask.getStartDate();
        CalendarDate resultEnd = superTask.getEndDate();
        
        //Compare to subtask 1's dates, since subtask 1 has been set
        //so that it will be the determining factor:
        assertTrue(sub1_start.getDay() == resultStart.getDay());
        assertTrue(sub1_start.getMonth() == resultStart.getMonth());
        assertTrue(sub1_start.getYear() == resultStart.getYear());
        
        assertTrue(sub1_end.getDay() == resultEnd.getDay());
        assertTrue(sub1_end.getMonth() == resultEnd.getMonth());
        assertTrue(sub1_end.getYear() == resultEnd.getYear());
	}
	
	/**
	 * This method tests the situation where the supertask has all its subtasks'
	 * with start dates later than the supertask's start date, and with all its
	 * subtasks' end dates earlier than the supertask's end date.  Thus, the 
	 * supertask should "shrink" its date range on both sides, hence why 
	 * this method is named testShrinkBoth.
	 */
	@Test
	public void testShrinkBoth() {
		//Supertask start and end dates:
		Calendar superStartD = Calendar.getInstance();
		superStartD.add(Calendar.DAY_OF_MONTH, 1);
		Calendar superEndD = Calendar.getInstance();
		superEndD.add(Calendar.DAY_OF_MONTH, 10);
		CalendarDate super_start = new CalendarDate(superStartD);
		CalendarDate super_end = new CalendarDate(superEndD);
		
		//Subtask 1 start and end dates (subtask 1 has the smallest range):
		Calendar sub1StartD = Calendar.getInstance();
		sub1StartD.add(Calendar.DAY_OF_MONTH, 2);
		Calendar sub1EndD = Calendar.getInstance();
		sub1EndD.add(Calendar.DAY_OF_MONTH, 9);
		CalendarDate sub1_start = new CalendarDate(sub1StartD);
		CalendarDate sub1_end = new CalendarDate(sub1EndD);
		
		//Subtask 2 start and end dates:
		Calendar sub2StartD = Calendar.getInstance();
		sub2StartD.add(Calendar.DAY_OF_MONTH, 4);
		Calendar sub2EndD = Calendar.getInstance();
		sub2EndD.add(Calendar.DAY_OF_MONTH, 8);
		CalendarDate sub2_start = new CalendarDate(sub2StartD);
		CalendarDate sub2_end = new CalendarDate(sub2EndD);
				
		//Subtask 3 start and end dates:
		Calendar sub3StartD = Calendar.getInstance();
		sub3StartD.add(Calendar.DAY_OF_MONTH, 3);
		Calendar sub3EndD = Calendar.getInstance();
		sub3EndD.add(Calendar.DAY_OF_MONTH, 7);
		CalendarDate sub3_start = new CalendarDate(sub3StartD);
		CalendarDate sub3_end = new CalendarDate(sub3EndD);
		
		//Create the tasks:
		Task superTask = tl.createTask(super_start, super_end, "super", 1, 80, "super desc", null);
		Task sub1 = tl.createTask(sub1_start, sub1_end, "sub1", 1, 80, "sub1 desc", superTask.getID());
		Task sub2 = tl.createTask(sub2_start, sub2_end, "sub2", 1, 80, "sub2 desc", superTask.getID());
		Task sub3 = tl.createTask(sub3_start, sub3_end, "sub3", 1, 80, "sub3 desc", superTask.getID());
		
		//Test the date compaction algorithm:
		superTask.setStartDate(tl.getEarliestStartDateFromSubTasks(superTask));
        superTask.setEndDate(tl.getLatestEndDateFromSubTasks(superTask));
        
        //Get the resultant dates:
        CalendarDate resultStart = superTask.getStartDate();
        CalendarDate resultEnd = superTask.getEndDate();
        
        //Compare to subtask 1's dates, since subtask 1 has been set
        //so that it will be the determining factor:
        assertTrue(sub1_start.getDay() == resultStart.getDay());
        assertTrue(sub1_start.getMonth() == resultStart.getMonth());
        assertTrue(sub1_start.getYear() == resultStart.getYear());
        
        assertTrue(sub1_end.getDay() == resultEnd.getDay());
        assertTrue(sub1_end.getMonth() == resultEnd.getMonth());
        assertTrue(sub1_end.getYear() == resultEnd.getYear());
	}
	
	/**
	 * This method tests the situation where the supertask has one or more subtasks
	 * whose start date is earlier than the supertask's start date, and with all its
	 * subtasks' end dates earlier than the supertask's end date.  Thus, the 
	 * supertask should "grow" its date range at the start date, and "shrink" its date 
	 * range at the end date, hence why this method is named testGrowShrink.
	 */
	@Test
	public void testGrowShrink() {
		//Supertask start and end dates:
		Calendar superStartD = Calendar.getInstance();
		superStartD.add(Calendar.DAY_OF_MONTH, 5);
		Calendar superEndD = Calendar.getInstance();
		superEndD.add(Calendar.DAY_OF_MONTH, 10);
		CalendarDate super_start = new CalendarDate(superStartD);
		CalendarDate super_end = new CalendarDate(superEndD);
		
		//Subtask 1 start and end dates (subtask 1 has the smallest range):
		Calendar sub1StartD = Calendar.getInstance();
		sub1StartD.add(Calendar.DAY_OF_MONTH, 1);
		Calendar sub1EndD = Calendar.getInstance();
		sub1EndD.add(Calendar.DAY_OF_MONTH, 9);
		CalendarDate sub1_start = new CalendarDate(sub1StartD);
		CalendarDate sub1_end = new CalendarDate(sub1EndD);
		
		//Subtask 2 start and end dates:
		Calendar sub2StartD = Calendar.getInstance();
		sub2StartD.add(Calendar.DAY_OF_MONTH, 4);
		Calendar sub2EndD = Calendar.getInstance();
		sub2EndD.add(Calendar.DAY_OF_MONTH, 8);
		CalendarDate sub2_start = new CalendarDate(sub2StartD);
		CalendarDate sub2_end = new CalendarDate(sub2EndD);
				
		//Subtask 3 start and end dates:
		Calendar sub3StartD = Calendar.getInstance();
		sub3StartD.add(Calendar.DAY_OF_MONTH, 3);
		Calendar sub3EndD = Calendar.getInstance();
		sub3EndD.add(Calendar.DAY_OF_MONTH, 7);
		CalendarDate sub3_start = new CalendarDate(sub3StartD);
		CalendarDate sub3_end = new CalendarDate(sub3EndD);
		
		//Create the tasks:
		Task superTask = tl.createTask(super_start, super_end, "super", 1, 80, "super desc", null);
		Task sub1 = tl.createTask(sub1_start, sub1_end, "sub1", 1, 80, "sub1 desc", superTask.getID());
		Task sub2 = tl.createTask(sub2_start, sub2_end, "sub2", 1, 80, "sub2 desc", superTask.getID());
		Task sub3 = tl.createTask(sub3_start, sub3_end, "sub3", 1, 80, "sub3 desc", superTask.getID());
		
		//Test the date compaction algorithm:
		superTask.setStartDate(tl.getEarliestStartDateFromSubTasks(superTask));
        superTask.setEndDate(tl.getLatestEndDateFromSubTasks(superTask));
        
        //Get the resultant dates:
        CalendarDate resultStart = superTask.getStartDate();
        CalendarDate resultEnd = superTask.getEndDate();
        
        //Compare to subtask 1's dates, since subtask 1 has been set
        //so that it will be the determining factor:
        assertTrue(sub1_start.getDay() == resultStart.getDay());
        assertTrue(sub1_start.getMonth() == resultStart.getMonth());
        assertTrue(sub1_start.getYear() == resultStart.getYear());
        
        assertTrue(sub1_end.getDay() == resultEnd.getDay());
        assertTrue(sub1_end.getMonth() == resultEnd.getMonth());
        assertTrue(sub1_end.getYear() == resultEnd.getYear());
	}
	
	/**
	 * This method tests the situation where the supertask has all its subtasks'
	 * with start dates later than the supertask's start date, and with all its
	 * subtasks' end dates being later than the supertask's end date.  Thus, the 
	 * supertask should "shrink" its date range at the start date, and "grow" its date 
	 * range at the end date, hence why this method is named testShrinkGrow.
	 */
	@Test
	public void testShrinkGrow() {
		//Supertask start and end dates:
		Calendar superStartD = Calendar.getInstance();
		superStartD.add(Calendar.DAY_OF_MONTH, 1);
		Calendar superEndD = Calendar.getInstance();
		superEndD.add(Calendar.DAY_OF_MONTH, 6);
		CalendarDate super_start = new CalendarDate(superStartD);
		CalendarDate super_end = new CalendarDate(superEndD);
		
		//Subtask 1 start and end dates (subtask 1 has the smallest range):
		Calendar sub1StartD = Calendar.getInstance();
		sub1StartD.add(Calendar.DAY_OF_MONTH, 3);
		Calendar sub1EndD = Calendar.getInstance();
		sub1EndD.add(Calendar.DAY_OF_MONTH, 10);
		CalendarDate sub1_start = new CalendarDate(sub1StartD);
		CalendarDate sub1_end = new CalendarDate(sub1EndD);
		
		//Subtask 2 start and end dates:
		Calendar sub2StartD = Calendar.getInstance();
		sub2StartD.add(Calendar.DAY_OF_MONTH, 4);
		Calendar sub2EndD = Calendar.getInstance();
		sub2EndD.add(Calendar.DAY_OF_MONTH, 8);
		CalendarDate sub2_start = new CalendarDate(sub2StartD);
		CalendarDate sub2_end = new CalendarDate(sub2EndD);
				
		//Subtask 3 start and end dates:
		Calendar sub3StartD = Calendar.getInstance();
		sub3StartD.add(Calendar.DAY_OF_MONTH, 5);
		Calendar sub3EndD = Calendar.getInstance();
		sub3EndD.add(Calendar.DAY_OF_MONTH, 9);
		CalendarDate sub3_start = new CalendarDate(sub3StartD);
		CalendarDate sub3_end = new CalendarDate(sub3EndD);
		
		//Create the tasks:
		Task superTask = tl.createTask(super_start, super_end, "super", 1, 80, "super desc", null);
		Task sub1 = tl.createTask(sub1_start, sub1_end, "sub1", 1, 80, "sub1 desc", superTask.getID());
		Task sub2 = tl.createTask(sub2_start, sub2_end, "sub2", 1, 80, "sub2 desc", superTask.getID());
		Task sub3 = tl.createTask(sub3_start, sub3_end, "sub3", 1, 80, "sub3 desc", superTask.getID());
		
		//Test the date compaction algorithm:
		superTask.setStartDate(tl.getEarliestStartDateFromSubTasks(superTask));
        superTask.setEndDate(tl.getLatestEndDateFromSubTasks(superTask));
        
        //Get the resultant dates:
        CalendarDate resultStart = superTask.getStartDate();
        CalendarDate resultEnd = superTask.getEndDate();
        
        //Compare to subtask 1's dates, since subtask 1 has been set
        //so that it will be the determining factor:
        assertTrue(sub1_start.getDay() == resultStart.getDay());
        assertTrue(sub1_start.getMonth() == resultStart.getMonth());
        assertTrue(sub1_start.getYear() == resultStart.getYear());
        
        assertTrue(sub1_end.getDay() == resultEnd.getDay());
        assertTrue(sub1_end.getMonth() == resultEnd.getMonth());
        assertTrue(sub1_end.getYear() == resultEnd.getYear());
	}

}
