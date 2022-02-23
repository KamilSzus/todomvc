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
public class WhenCompletingATask {

    @Managed(driver = "chrome")
    WebDriver webDriver;

    @Steps
    ListAction listAction;

    @Before
    public void openApplication(){
        listAction.openApplication();
    }

    @Test
    public void activeTasksShouldNotShowCompletedTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        listAction.addTasksWithName("Feed The Cat","Walk the dog");
        // Complete "Feed the cat"
        listAction.completeTask("Feed The Cat");
        // Filter by "Active"
        listAction.filterBy("Active");
        // Check that only "Walk the dog" appears
        reportThat("check if tasks list contains expected actions",
                ()->assertThat(listAction.getTasks())
                        .containsExactly("Walk the dog")
                        .hasSize(1));
    }

    @Test
    public void completedTasksShouldNotShowActiveTasks() {
        // Add "Feed the cat" and "Walk the dog" to the list
        listAction.addTasksWithName("Feed The Cat","Walk the dog");
        // Complete "Feed the cat"
        listAction.completeTask("Feed The Cat");
        // Filter by "Completed"
        listAction.filterBy("Completed");
        // Check that only "Feed the cat" appears
        reportThat("check if tasks list contains expected actions",
                ()->assertThat(listAction.getTasks())
                        .containsExactly("Feed The Cat")
                        .hasSize(1));
    }
}