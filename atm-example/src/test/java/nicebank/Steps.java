package nicebank;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import transforms.MoneyConverter;
import cucumber.api.java.en.Then;

import org.junit.*;

public class Steps {

    private Money balance = new Money();

    class Account{
        public Money deposit(Money amount){
            return balance = balance.add(amount);
        }

        public Money getBalance(){
            return balance;
        }
    }

    class Teller{
        public void withdrawFrom(Account account, int dollars){

        }
    }

    class KnowsMyAccount{

        private Account myAccount;

        public Account getMyAccount(){
            if(myAccount == null){
                myAccount = new Account();
            }
            return myAccount;   
        }
    }

    KnowsMyAccount helper;

    public Steps(){
        helper = new KnowsMyAccount();
    }

    @Given("^I have deposited \\$(\\d+\\.\\d+) in my account$") 
    public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class)Money amount) throws Throwable{
        
        helper.getMyAccount().deposit(amount);

        Assert.assertEquals("Incorrect account balance -", 
                                amount, helper.getMyAccount().getBalance());
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int amount) throws Throwable {
        Teller teller = new Teller();
        teller.withdrawFrom(helper.getMyAccount(), amount);
    }

     @Then("^\\$(\\d+) should be dispensed$")
     public void $_should_be_dispensed(int amount) throws Throwable{
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
     }
    
}
