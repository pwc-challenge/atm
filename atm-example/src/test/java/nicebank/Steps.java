package nicebank;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import nicebank.support.KnowsTheDomain;
import transforms.MoneyConverter;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertEquals;

public class Steps {

    KnowsTheDomain helper;

    public Steps(){
        helper = new KnowsTheDomain();
    }

    @Given("^I have deposited \\$(\\d+\\.\\d+) in my account$") 
    public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class)Money amount) throws Throwable{
        
        helper.getMyAccount().deposit(amount);

        assertEquals("Incorrect account balance -", 
                                amount, helper.getMyAccount().getBalance());
    }

    @When("^I request \\$(\\d+)$")
    public void i_request_$(int dollars) throws Throwable {

        helper.getTeller().withdrawFrom(helper.getMyAccount(), dollars);

     }

     @Then("^\\$(\\d+) should be dispensed$")
     public void $_should_be_dispensed(int dollars) throws Throwable{

        assertEquals("Incorrect amount dispensed -",
                                dollars, helper.getCashSlot().getContents());

     }
    
}
