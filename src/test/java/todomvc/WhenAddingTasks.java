package todomvc;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.listAction.ListAction;

import static net.serenitybdd.core.Serenity.reportThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenAddingTasks {

    @Managed(uniqueSession = true, driver = "chrome")
    WebDriver webDriver;

    @Steps
    ListAction listAction;

    @Before
    public void openApplication(){
        listAction.openApplication();
    }

    @Test
    public void addingASingleTask() {
        // Add "Feed The Cat" to the list
        listAction.addTasksWithName("Feed The Cat");
        // Check that "Feed The Cat" appears in the list
        reportThat("check if tasks list contains expected actions",
                ()->assertThat(listAction.getTasks())
                        .containsExactly("Feed The Cat")
                        .hasSize(1));
    }

    @Test
    public void addingMultipleTasks() {
        // Add "Feed The Cat" and "Walk the dog" to the list
        listAction.addTasksWithName("Feed The Cat","Walk the dog");
        // Check that they all appear in the list
        reportThat("check if tasks list contains expected actions",
                ()->assertThat(listAction.getTasks())
                        .containsExactly("Feed The Cat","Walk the dog")
                        .hasSize(2));
    }
}