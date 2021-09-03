package nicebank;

public class CashSlot {

    private int contents;

    public void dispense(int dollars){
        contents = dollars;
    }

    public int getContents() {
        return contents;
    }
}
