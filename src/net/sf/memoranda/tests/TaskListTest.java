package net.sf.memoranda.tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectManager;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.TaskListImpl;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.util.Configuration;

public class TaskListTest {

    TaskList myTaskList;

    @Before
    public void init() {

        // setup for test project
        String title = "Test Project";
        Calendar today = Calendar.getInstance();
        Calendar later = Calendar.getInstance();
        later.add(Calendar.DAY_OF_MONTH, 20);
        CalendarDate startD = new CalendarDate(today);
        CalendarDate endD = new CalendarDate(later);
        Project proj = ProjectManager.createProject(title, startD, endD);
        CurrentProject.set(proj);
        myTaskList = new TaskListImpl(proj);
    }

    @Test
    public void createTask() {
        // setup dates for tasks
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        e.add(Calendar.DAY_OF_MONTH, 10);
        CalendarDate sd = new CalendarDate(s);
        CalendarDate ed = new CalendarDate(e);

        Task rootTask = myTaskList.createTask(sd, ed, "root", 0, 0, "", null);

    }
    
    @Test
    public void testCreateTask() {

        // Enforce Auto_Aggregation for Test
        Configuration.put("AUTO_AGGREGATION", "yes");
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.DAY_OF_MONTH, 10);
        CalendarDate startDate = new CalendarDate(startCalendar);
        CalendarDate endDate = new CalendarDate(endCalendar);
        
        Task rootTask = myTaskList.createTask(startDate, endDate, "root task", 0, 0, "description", null);
        
        String rootTaskID = rootTask.getID();
        
        Task childTaskOne = myTaskList.createTask(startDate, endDate, "child task 1", 0, 1, "description", rootTaskID);
        childTaskOne.setProgress(80);
        
        assertEquals(1, childTaskOne.getEffort());
        assertEquals(80, childTaskOne.getProgress());
        
        Task childTaskTwo  = myTaskList.createTask(startDate, endDate, "child task 2", 0, 2, "description", rootTaskID);
        childTaskTwo.setProgress(10);
        
        assertEquals(2, childTaskTwo.getEffort());
        assertEquals(10, childTaskTwo.getProgress());
    	
    }
}
