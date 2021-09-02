package nicebank;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Money money = new Money("$200.00");
        System.out.println(money.toString());
    }
}
