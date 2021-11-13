import com.company.service.ValidationServer;
import com.company.service.impl.ValidationServerImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationServerTest {
    private static ValidationServer validationServer;


    @BeforeAll
    static void setup() {
        validationServer = new ValidationServerImpl();
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestCanBuildInvalidNumber({0})")
    @CsvSource({
            "da2",
            "---23",
            "55.5555555addasda",
            "+++123.243434",
            "fdf222",
            "333fff"
    })
    void TestCanBuildInvalidNumber(String token) {
        boolean isNumber = validationServer.canBuildNumber(token);
        assertFalse(isNumber);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestCanBuildValidNumber({0})")
    @CsvSource({
            "2",
            "2",
            "55.5555555",
            "123.243434",
            "222.01",
            "333"
    })
    void TestCanBuildValidNumber(String token) {
        boolean isNumber = validationServer.canBuildNumber(token);
        assertTrue(isNumber);
    }


    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestCanValidateBracketsSequence({0})")
    @CsvSource({
            "()",
            "(())",
            "((()())(()))",
            "()(())",
            "()(()())",
    })
    void TestCanValidateBracketsSequence(String expression) {
        List<String> items = Arrays.asList(expression.split(""));
        boolean isValid = validationServer.validateBracketsSequence(items.iterator());
        assertTrue(isValid);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestCanInvalidBracketsSequence({0})")
    @CsvSource({
            "())",
            "(()",
            "()(()()",
            "(()(())()",
    })
    void TestCanInvalidBracketsSequence(String expression) {
        List<String> items = Arrays.asList(expression.split(""));
        boolean isValid = validationServer.validateBracketsSequence(items.iterator());
        assertFalse(isValid);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestValidateUnaryOperatorBetweenBrackets({0})")
    @CsvSource({
            "-5 )",
            "-5.555555 )",
            "-3.01 )",
    })
    void TestValidateUnaryOperatorBetweenBrackets(String expression) {
        List<String> items = Arrays.asList(expression.split(" "));
        String currentToken = items.get(0);
        String nextToken = items.get(1);
        boolean isValid = validationServer.validateUnaryOperatorBetweenBrackets(nextToken,currentToken);
        assertTrue(isValid);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestInValidateUnaryOperatorBetweenBrackets({0})")
    @CsvSource({
            "+5 )",
            "+5.555555 )",
            "--3.01 )",
            "-3.01 -",
    })
    void TestInValidateUnaryOperatorBetweenBrackets(String expression) {
        List<String> items = Arrays.asList(expression.split(" "));
        String currentToken = items.get(0);
        String nextToken = items.get(1);
        boolean isValid = validationServer.validateUnaryOperatorBetweenBrackets(nextToken,currentToken);
        assertFalse(isValid);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestValidateLengthExpression({0})")
    @CsvSource({
            "( -5 ) + 3",
            "20 + 20",
    })
    void TestValidateLengthExpression(String expression) {
        List<String> items = Arrays.asList(expression.split(" "));

        boolean isValid = validationServer.validateLengthExpression(items);
        assertTrue(isValid);
    }

    @ParameterizedTest(name = "Тест метода ValidationServerTest.TestInValidateLengthExpression({0})")
    @CsvSource({
            "5",
            "-5",
    })
    void TestInValidateLengthExpression(String expression) {
        List<String> items = Arrays.asList(expression.split(" "));

        boolean isValid = validationServer.validateLengthExpression(items);
        assertFalse(isValid);
    }
}
