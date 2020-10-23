/**
 * @autor Chekmarev Andrey
 * 3rd year, 7th group
 * @version 2.0
 *  Main class
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the equation");
        String eq = in.nextLine();
        eq = eq.replaceAll("pi", "3.14"); //pi constant replacing
        System.out.println("Enter count of operands");
        int count = in.nextInt();
        for(int i = 0; i < count; i++){ //variables replacing
            System.out.println("Enter name of operand");
            String name = in.nextLine();
            name = in.nextLine();
            System.out.println("Enter value of operand");
            String value = in.nextLine();
            eq = eq.replaceAll(name, value);
        }
        Eval.setStr(eq);
        System.out.println(Eval.parse());
    }
}