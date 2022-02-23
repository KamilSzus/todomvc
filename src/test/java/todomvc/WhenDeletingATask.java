package todomvc;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.listAction.ListAction;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityRunner.class)
public class WhenDeletingATask {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    ListAction listAction;

    @Before
    public void openApplication(){
        listAction.openApplication();
    }

    @Test
    public void deletedItemsShouldDisappearFromTheList() {
        // Add "Feed the cat" and "Walk the dog" to the list
        listAction.addTasksWithName("Feed the cat","Walk the dog");
        // Delete "Feed the cat"
        listAction.deleteTask("Feed the cat");
        // Check that only "Walk the dog" appears
        Serenity.reportThat("",
                ()-> assertThat(listAction.getTasks())
                        .containsExactly("Walk the dog")
                        .hasSize(1));
    }
}