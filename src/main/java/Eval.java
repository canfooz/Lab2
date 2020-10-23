/**
 * @autor Chekmarev Andrey
 * 3rd year, 7th group
 * @version 2.0
 *  Parser class
 */
public class Eval {

    /** Position field */
    static int pos = -1;

    /** Processed sign field */
    static char ch;

    /** Current string value field */
    static String str;

    /**
     * Getter-method will
     * @return current str from field
     */
    static public String getStr() {
        return str;
    }

    /**
     * Setter-method
     * @param newStr - some String value
     * Assign the newStr to the str
     */
    static public void setStr(String newStr) {
        str = newStr;
    }

    /**
     * If the string doesn't end
     * Assign the char of current position to the ch
     * else assign "!"
     */
    static void nextChar() {
        ch = (++pos < getStr().length()) ? getStr().charAt(pos) : '!' ;
    }

    /**
     * @param charToEat - the desired char in the string
     * @return true if the char are identical with current ch
        false if the characters aren't identical with current ch
     * Skips spaces
     */
    static boolean eat(char charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    /**
     * Sets the starting position
     * Accesses the method parseExpression()
     * @return value of parseExpression();
     * @throws RuntimeException if position isn't starting
     */
    static double parse() {
        nextChar();
        double x = parseExpression();
        if (pos < getStr().length()) throw new RuntimeException("Unexpected: " + (char)ch);
        return x;
    }

    /**
     * Accesses the method parseTerm() to x
     * Implements addition and subtraction with next parseTerm()
     * 2nd order
     * @return value of parseTerm() if addition and subtraction weren't implement
     */
    static double parseExpression() {
        double x = parseTerm();
        for (;;) {
            if      (eat('+')) x += parseTerm(); //addition
            else if (eat('-')) x -= parseTerm(); //subtraction
            else return x;
        }
    }

    /**
     * Accesses the method parseFactor() to x
     * Implements multiplication and division with next parseTerm()
     * 1st order
     * @return value of parseFactor() if multiplication and division weren't implement
     */
    static double parseTerm() {
        double x = parseFactor();
        for (;;) {
            if      (eat('*')) x *= parseFactor(); //multiplication
            else if (eat('/')) x /= parseFactor(); //division
            else return x;
        }
    }


    /**
     * Defines the sign of the expression or number
     * Selects and re-selects the beginning of a new expression
     * until it reaches the minimum expression(final parentheses)
     * 0 order
     * Selects a number or function and converts it to double
     * Recursively accesses the method parseExpression()
     * @return x - the final value of the expression
     */
    static double parseFactor() {
        if (eat('+')) return parseFactor(); //unary plus
        if (eat('-')) return -parseFactor(); //unary minus

        double x;
        int startPos = pos;
        if (eat('(')) { //parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { //numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            x = Double.parseDouble(getStr().substring(startPos, pos));
        } else if (ch >= 'a' && ch <= 'z') { //functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = getStr().substring(startPos, pos);
            x = parseFactor();
            if (func.equals("sqrt")) x = Math.sqrt(x);
            else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
            else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
            else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
            else throw new RuntimeException("Unknown function: " + func);
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        if (eat('^')) x = Math.pow(x, parseFactor()); //exponentiation
        return x;
    }
}
