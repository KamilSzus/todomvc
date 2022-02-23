package todomvc.listAction;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;

import java.util.Arrays;
import java.util.List;

public class ListAction extends UIInteractionSteps {

    @Step("Adding task with name '{0}' to list")
    public void addTasksWithName(String... tasks) {
        Arrays.stream(tasks)
                .toList()
                .forEach((task)->
                        $(".new-todo").typeAndEnter(task));
    }

    @Step("Open the TodoMVC application")
    public void openApplication(){
        openUrl("https://todomvc.com/examples/angularjs/#/");
    }

    public List<String> getTasks() {
        return findAll(".todo-list label").textContents();
    }

    @Step("Complete '{0} task'")
    public void completeTask(String task) {
        $("//label[.='{0}']/preceding-sibling::input[@type='checkbox']",task).click();
    }

    @Step("filter by '{0}' task")
    public void filterBy(String state) {
        $("//*[@class='filters']//a[.='{0}']",state).click();
    }
}
