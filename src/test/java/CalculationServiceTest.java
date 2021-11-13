import com.company.exeptions.IllegalOperationException;
import com.company.exeptions.SyntaxErrorCalculator;
import com.company.service.CalculatorService;
import com.company.service.impl.CalculatorServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculationServiceTest {

    private static CalculatorService calculatorService;

    @BeforeAll
    static void setup() {
        calculatorService = new CalculatorServiceImpl();
    }

    @ParameterizedTest(name = "Тест метода CalculationServiceTest.SimpleSumWithTwoNumbers({0})={1}")
    @CsvSource({
            "5 + 3, 8",
            "3.3 + 5.1, 8.4",
            "4 - 20, -16",
            "5 + 2.52, 7.52",
            "5 * 4, 20",
            "7.2 * 8, 57.6",
            "14 / 2, 7",
            "17 / 2, 8.5",
    })
    void TestSimpleSumWithTwoNumbers(String expression, double resultValue) throws Exception {
        double value = calculatorService.execute(expression);
        assertThat(resultValue, closeTo(value, 0.0000001));
    }

    @ParameterizedTest(name = "Тест метода CalculationServiceTest.TestCalculateExpression({0})={1}")
    @CsvSource({
            "( 5 ) + ( 3 ), 8",
            "( 3.3 + 5.1 ), 8.4",
            "( -4 ) - ( ( -20 ) + 1 ), 15",
            "( -5 ) + ( 6 - 3 ) * 4, 7",
            "( 5 * 4 ) * ( 3 - 2 ), 20",
            "( -7.2 ) * ( -8 ), 57.6",
            "( 30 / 2 ) / ( 5 * 3 ) - 8, -7",
            "( 20 / 2 ) / ( ( 3 + ( -2 ) ) * 2 ), 5",
    })
    void TestCalculateExpression(String expression, double resultValue) throws Exception {
        double value = calculatorService.execute(expression);
        assertThat(resultValue, closeTo(value, 0.0000001));
    }

    @ParameterizedTest(name = "Тест метода CalculationServiceTest.TestInvalidBracketsCalculateExpression({0}) c исключением SyntaxErrorCalculator")
    @CsvSource({
            "3",
            "(5 ) + ( 3 )",
            "( ( 3.3 + 5.1 )",
            "5 + 5 )",
            "( 3 + 2",
            "( ( ( ( ) ) ) "
    })
    void TestInvalidBracketsCalculateExpression(String expression) throws Exception {
        assertThrows(SyntaxErrorCalculator.class, ()->calculatorService.execute(expression));
    }

    @ParameterizedTest(name = "Тест метода CalculationServiceTest.TestInvalidOperatorCalculateExpression({0}) c исключением IllegalOperationException")
    @CsvSource({
            "( 5 ) ++ ( 3 )",
            "( --5 ) + ( 3 )",
            "( +5 ) + ( 3 )",
            "( 5 ) ** ( 3 )",
            "( 5 - 3 - )",
            "+ ( 5 - 3 )",
    })
    void TestInvalidOperatorCalculateExpression(String expression) throws Exception {
        assertThrows(IllegalOperationException.class, ()->calculatorService.execute(expression));
    }

    @ParameterizedTest(name = "Тест метода CalculationServiceTest.TestInvalidDivisionByZero({0}) c исключением IllegalOperationException")
    @CsvSource({
            "5 / 0",
    })
    void TestInvalidDivisionByZero(String expression) throws Exception {
        assertThrows(ArithmeticException.class, ()->calculatorService.execute(expression));
    }
}
