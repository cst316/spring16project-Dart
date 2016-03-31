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
import net.sf.memoranda.util.Configuration;

public class TaskListTest {

    TaskList tl;

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
        tl = new TaskListImpl(proj);
    }

    @Test
    public void createTask() {
        // setup dates for tasks
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        e.add(Calendar.DAY_OF_MONTH, 10);
        CalendarDate sd = new CalendarDate(s);
        CalendarDate ed = new CalendarDate(e);

        Task root = tl.createTask(sd, ed, "root", 0, 0, "", null);

    }
}
