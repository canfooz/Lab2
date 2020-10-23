import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EvalTest {

    @BeforeEach
    public void RefreshString() {
        Eval.setStr("");
    }

    @Test
    public void EvalParsesDefaultExpression() {
        String expression = "2+3*5";
        double expectedValue = 17;
        Eval.setStr(expression);
        Assertions.assertEquals(expectedValue, Eval.parse());
    }

    @Test
    public void EvalParsesEquationWithSpaces() {
        String expression = "2+ 3 *5";
        double expectedValue = 17;
        Eval.setStr(expression);
        Assertions.assertEquals(expectedValue, Eval.parse());
    }

    @Test
    public void EvalParsesEquationWithParentheses() {
        String expression = "2+3*(5-2*2)";
        double expectedValue = 5;
        Eval.setStr(expression);
        Assertions.assertEquals(expectedValue, Eval.parse());

    }

    @Test
    public void EvalParsesEquationWithFunctions() {
        String expression = "sin(0)+cos(0)";
        double expectedValue = 1;
        Eval.setStr(expression);
        Assertions.assertEquals(expectedValue, Eval.parse());

    }

    @Test
    public void EvalThrowsExceptionForUnknownFunction(){
        String expression = "2+tanxhz(4)";
        Eval.setStr(expression);
        Assertions.assertThrows(RuntimeException.class, Eval::parse);

    }

    @Test
    public void EvalThrowsExceptionForWrongExpression ( ) {
        String expression = "2+4abc";
        Eval.setStr(expression);
        Assertions.assertThrows(RuntimeException.class, Eval::parse);
    }

}
