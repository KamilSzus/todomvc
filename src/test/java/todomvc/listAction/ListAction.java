package todomvc.listAction;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static todomvc.listAction.Form.COMPLETE_CHECKBOX;
import static todomvc.listAction.Form.DESTROY_BUTTON;
import static todomvc.listAction.Form.FILTERS_BY;
import static todomvc.listAction.Form.LABEL_WITH_TASK;
import static todomvc.listAction.Form.NEW_TODO_TASK;
import static todomvc.listAction.Form.TASKS_LIST;

public class ListAction extends UIInteractionSteps {

    @Step("Adding task with name '{0}' to list")
    public void addTasksWithName(String... tasks) {
        Arrays.stream(tasks)
                .toList()
                .forEach((task)->
                        $(NEW_TODO_TASK).typeAndEnter(task));
    }

    @Step("Adding task with name '{0}' to list")
    public void addTasksWithName(List<String> tasks) {
        tasks.forEach((task)->
                        $(NEW_TODO_TASK).typeAndEnter(task));
    }

    @Step("Open the TodoMVC application")
    public void openApplication(){
        openPageNamed("home");
    }

    public List<String> getTasks() {
        return findAll(TASKS_LIST).textContents();
    }

    @Step("Complete '{0} task'")
    public void completeTask(String task) {
        $(COMPLETE_CHECKBOX,task).click();
    }

    @Step("filter by '{0}' task")
    public void filterBy(String state) {
        $(FILTERS_BY,state).click();
    }

    @Step("Delete task witch name '{0}'")
    public void deleteTask(String task) {
        WebElement element = $(LABEL_WITH_TASK,task);
        WebElement destroyButton = $(DESTROY_BUTTON);

        withAction().moveToElement(element)
                .moveToElement(destroyButton)
                .click()
                .perform();

    }
}
