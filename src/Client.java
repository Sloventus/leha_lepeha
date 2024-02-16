

public class Client {
    private String id;
    private long balance;
//------------------------------------------------------------------------------
    private boolean status;
//------------------------------------------------------------------------------
    Client(String id, long balance){
        this.balance = balance;
        this.id = id;
        status = true;
    }
//------------------------------------------------------------------------------

    public void setBalance(long balance){this.balance = balance;}
    public long getBalance(){return balance;}
    public void addBalance(long money){this.balance += money;}

    public void setId(String id){this.id = id;}
    public String getId(){return id;}

}