package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import transforms.MoneyConverter;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

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

        private CashSlot cashSlot;

        public Teller(CashSlot cashSlot){
            this.cashSlot = cashSlot;
        }

        public void withdrawFrom(Account account, int dollars){
            cashSlot.dispense(dollars);
        }
    }

    class CashSlot{
        
        private int contents; 

        public int contents(){
            return contents;
        }

        public void dispense(int dollars) {
            contents = dollars;
        }
    }

    class KnowsTheDomain{

        private Account myAccount;
        private CashSlot cashSlot;
        private Teller teller;

        public Account getMyAccount(){
            if(myAccount == null){
                myAccount = new Account();
            }
            return myAccount;   
        }

        public CashSlot getCashSlot(){
            if(cashSlot == null){
                cashSlot = new CashSlot();
            }
            return cashSlot;
        }

        public Teller getTeller(){
            if(teller == null){
                teller = new Teller(getCashSlot());
            }
            return teller;
        }
    }

    KnowsTheDomain helper;

    public Steps(){
        helper = new KnowsTheDomain();
    }

    @Given("^I have deposited \\$(\\d+\\.\\d+) in my account$") 
    public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class)Money amount) throws Throwable{
        
        helper.getMyAccount().deposit(amount);

        Assert.assertEquals("Incorrect account balance -", 
                                amount, helper.getMyAccount().getBalance());
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int dollars) throws Throwable {
        helper.getTeller().withdrawFrom(helper.getMyAccount(), dollars);
    }

     @Then("^\\$(\\d+) should be dispensed$")
     public void $_should_be_dispensed(int dollars) throws Throwable{

        assertEquals("Incorrect amount dispensed -",
                                dollars, helper.getCashSlot().contents());
     }
    
}
