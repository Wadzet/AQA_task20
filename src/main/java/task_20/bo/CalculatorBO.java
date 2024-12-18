package task_20.bo;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task_20.po.CalculatorPO;

public class CalculatorBO {

    private static final Logger logger = LogManager.getLogger(CalculatorBO.class);
    private final CalculatorPO calculatorPO;

    public CalculatorBO() {
        calculatorPO = new CalculatorPO();
    }

    @Step("Calculate {input1}% of {input2}")
    public String calculatePercentage(String input1, String input2) {
        logger.info("Calculating percentage: {}% of {}", input1, input2);
        calculatorPO
                .enterNumber(input1)
                .pressPercentage()
                .enterNumber(input2)
                .pressEquals();
        String result = calculatorPO.getResult();
        logger.info("Result: {}", result);
        return result;
    }
}
