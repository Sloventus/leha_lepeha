import java.math.BigInteger;
import java.util.*;
import java.util.random.RandomGenerator;

public class Bank {
    private Map<String,Client> bookBank;
    private HashSet<String> blackList = new HashSet<>();
    public HashSet<String> getBlackList(){return this.blackList;}

    public Bank(HashMap<String,Client> bookBank){
        this.bookBank = bookBank;
    }

    //остаток на счете клиента--------------------------------------------------------------
    public long balance(String id){
        return bookBank.get(id).getBalance();
    }

    //сумма всех средств------------------------------------------------------------------
    public BigInteger allMoney(){
        BigInteger sum = BigInteger.valueOf(0);
        for (String key : bookBank.keySet()){
            BigInteger adder = BigInteger.valueOf(bookBank.get(key).getBalance());
            sum = sum.add(adder);
        }
        return sum;
    }

    //перевод между 2 счетами----------------------------------------------------------------
    public void transfer(String idSource, String idDestination, long money){
        if ( blackList.contains(idDestination) || blackList.contains(idSource)){
            System.out.printf("Клиент заблокирован (%s, %s)\n", idSource, idDestination);
        }else {
            if (money <= 50000) {
                calculation(idSource, idDestination, money);
            }else if (checkTransfer()){
                blackList.add(idSource);
                blackList.add(idDestination);
                System.out.println("Транзакция отклонена");
            } else {
                calculation(idSource, idDestination, money);
            }
        }
    }

    private void calculation(String idSource, String idDestination, long money) {
        bookBank.get(idDestination).addBalance(money);
        bookBank.get(idSource).addBalance(money * (-1));
        System.out.printf("клиент %s перевел %d клиенту %s\n", idSource, money, idDestination);
    }

    private boolean checkTransfer(){
        return RandomGenerator.getDefault().nextBoolean();
    }

    public void printBank(){
        for (String key : bookBank.keySet()){
            System.out.println(key + " " + bookBank.get(key).getBalance());
        }
    }

}
