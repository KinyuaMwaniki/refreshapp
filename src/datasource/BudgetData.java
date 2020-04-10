package datasource;

public class BudgetData {

    private String rent;
    private String deductions;
    private String garbage;
    private String electricity;
    private String gas;
    private String shopping;
    private String airtime;
    private String tv;
    private String transport;
    private String food;
    private String entertainment;
    private int totalBudget;

    public BudgetData(String rent, String deductions, String garbage, String electricity, String gas, String shopping, String airtime, String tv, String transport, String food, String entertainment) {
        this.rent = rent;
        this.deductions = deductions;
        this.garbage = garbage;
        this.electricity = electricity;
        this.gas = gas;
        this.shopping = shopping;
        this.airtime = airtime;
        this.tv = tv;
        this.transport = transport;
        this.food = food;
        this.entertainment = entertainment;
      
    }

    public String getRent() {
        return rent;
    }

    public String getDeductions() {
        return deductions;
    }

    public String getGarbage() {
        return garbage;
    }

    public String getElectricity() {
        return electricity;
    }

    public String getGas() {
        return gas;
    }

    public String getShopping() {
        return shopping;
    }

    public String getAirtime() {
        return airtime;
    }

    public String getTv() {
        return tv;
    }

    public String getTransport() {
        return transport;
    }

    public String getFood() {
        return food;
    }

    public String getEntertainment() {
        return entertainment;
    }

    

    
}
