package command;

import main.TaskList;
import main.Storage;
import main.Ui;

public class SearchCommand extends Command {

    public SearchCommand(String description) {
        super(description);
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.searchTask(getDescription());
    }
}
