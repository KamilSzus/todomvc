package todomvc.listAction;

import org.openqa.selenium.By;

class Form {
    static final By NEW_TODO_TASK = By.className("new-todo");
    static final String TASKS_LIST = ".todo-list label";
    static final String COMPLETE_CHECKBOX = "//label[.='{0}']/preceding-sibling::input[@type='checkbox']";
    static final String FILTERS_BY = "//*[@class='filters']//a[.='{0}']";
    static final String LABEL_WITH_TASK = "//label[.='{0}']";
    static final String DESTROY_BUTTON = ".destroy";
}
