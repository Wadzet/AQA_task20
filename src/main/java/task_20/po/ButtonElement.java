package task_20.po;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

public class ButtonElement extends BaseElement {
    private static final Logger logger = LogManager.getLogger(ButtonElement.class);

    public ButtonElement(WebElement element) {
        super(element);
    }

    public void click() {
        logger.info("Clicking button");
        element.click();
    }
}
