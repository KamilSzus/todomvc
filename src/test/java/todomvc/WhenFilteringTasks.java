package todomvc;


import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import todomvc.listAction.ListAction;

import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static net.serenitybdd.core.Serenity.reportThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class WhenFilteringTasks {

    @Managed(driver = "chrome",options = "--hedless")
    WebDriver webDriver;

    @Steps
    ListAction listAction;

    private String filter;
    private String taskToComplete;
    private List<String> tasksList;
    private List<String> result;

    public WhenFilteringTasks(String filter, List<String> tasksList, String taskToComplete, List<String> result) {
        this.filter = filter;
        this.taskToComplete = taskToComplete;
        this.tasksList = tasksList;
        this.result = result;
    }

    @TestData(columnNames = "Filter by, Tasks list, Completed tasks, Expected result")
    public static Collection<Object[]> testData(){
        return asList(
                new Object[][]{
                        {"Active", asList("Feed The Cat","Walk the dog"), "Feed The Cat", List.of("Walk the dog")}
                        ,{"Completed", asList("Feed The Cat","Walk the dog"), "Feed The Cat", List.of("Feed The Cat")}
                        ,{"All", asList("Feed The Cat","Walk the dog"), "Feed The Cat", asList("Feed The Cat","Walk the dog")}
                }
        );
    }

    @Test
    public void shouldDisplayedCorrectlyFilteringTasks(){
        listAction.openApplication();
        listAction.addTasksWithName(tasksList);

        listAction.completeTask(taskToComplete);
        listAction.filterBy(filter);

        reportThat("check if tasks list contains expected actions",
                ()->assertThat(listAction.getTasks()).containsExactlyElementsOf(result));
    }
}
