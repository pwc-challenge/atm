package nicebank;

public class Account {
    private Money balance = new Money();

    public void deposit(Money amount){
        balance = balance.add(amount);
    }
}
