package main;

import exception.DukeException;
import task.*;

import java.util.ArrayList;

/**
 * This class is used to handle operations regarding the list of tasks
 * such as adding or deleting
 */
public class TaskList {

    /**
     * Creates TaskList object with the given taskList by the Storage class
     * @param taskList ArrayList of type Task to be be used
     */
    private final ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of taskList to be used for loops or other operations
     * @return size of taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Displays the list of tasks in taskList
     */
    public void list() {
        System.out.println("________________________________");
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.valueOf(i + 1) + ". " + taskList.get(i).getString();
            System.out.println(output);
        }
        System.out.println("________________________________");
    }

    /**
     * Displays the list of task where description contains the word
     * @param word String to be found that exists in description of task
     */
    public void searchTask(String word) {
        ArrayList<Task> foundTask = new ArrayList<>();
        for (Task i: taskList) {
            if (i.getString().contains(word)) {
                foundTask.add(i);
            }
        }
        System.out.println("________________________________");
        if (foundTask.isEmpty()) {
            System.out.println("No such task found!");
        } else {
            int counter = 1;
            for (Task i : foundTask) {
                String output = String.valueOf(counter) + ". " + i.getString();
                System.out.println(output);
                counter += 1;
            }
        }
        System.out.println("________________________________");
    }

    /**
     * Adds task of type todo into taskList
     * @param description The description of todo task
     * @return The added task to be displayed by UI class
     */
    public Task addTodo(String description) {
        Task current = new Todo(description);
        taskList.add(current);
        return current;
    }

    /**
     * Adds task of type Event into taskList
     * @param description The description of Event task
     * @return The added task to be displayed by UI class
     */
    public Task addEvent(String description) {
        String[] taskDetails = description.split(" /from ");
        String[] taskTimings = taskDetails[1].split(" /to ");
        Task current = new Event(taskDetails[0], taskTimings[0], taskTimings[1]);
        taskList.add(current);
        return current;
    }

    /**
     * Adds task of type Deadline into taskList
     * @param description The description of Deadline task
     * @return The added task to be displayed by UI class
     */
    public Task addDeadline(String description) {
        String[] taskDetails = description.split(" /by ");
        Task current = new Deadline(taskDetails[0], taskDetails[1]);
        taskList.add(current);
        return current;
    }

    /**
     * Takes in the index of task to be marked as done and returns the task for UI to display
     * Throws error if it is already marked as done or index not in list
     * @param index The index of task in taskList to be marked as done
     * @return the tasked that is marked to be displayed by UI class
     */
    public Task mark(int index) throws DukeException{
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (curr.isDone()) {
                throw new DukeException("It is already marked!");
            }
            curr.mark();
            return curr;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }
    /**
     * Takes in the index of task to be marked as undone and returns the task for UI to display
     * Throws error if it is already marked as done or index not in list
     * @param index The index of task in taskList to be marked as undone
     * @return the tasked that is marked to be displayed by UI class
     */
    public Task unmark(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task curr = taskList.get(index);
            if (!curr.isDone()) {
                throw new DukeException("It is already unmarked!");
            }
            curr.mark();
            return curr;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }
    /**
     * Takes in the index of task to be deleted and returns the task for UI to display
     * Throws error if index not in list
     * @param index The index of task in taskList to be deleted
     * @return the tasked that is deleted to be displayed by UI class
     */
    public Task delete(int index) {
        try {
            if (index < 0 || index >= taskList.size()){
                throw new DukeException("Invalid position!");
            }
            return taskList.remove(index);
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
