package task_20.po;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class BaseElement {
    protected WebElement element;
    private static final Logger logger = LogManager.getLogger(BaseElement.class);

    public BaseElement(WebElement element) {
        this.element = element;
    }

    public boolean isDisplayed() {
        logger.info("Checking if element is displayed");
        return element.isDisplayed();
    }

    public String getText() {
        logger.info("Getting element text");
        return element.getText();
    }
}
