package todomvc.listAction;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

import java.util.List;

public class ListAction extends UIInteractionSteps {

    @Step("Adding task with name '{0}' to list")
    public void addTasksWithName(String tasks) {
        $(".new-todo").typeAndEnter(tasks);
    }

    @Step("Open the TodoMVC application")
    public void openApplication(){
        openUrl("https://todomvc.com/examples/angularjs/#/");
    }

    public List<String> getTasks() {
        return findAll(".todo-list label").textContents();
    }
}