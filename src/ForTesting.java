import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.random.RandomGenerator;

public class ForTesting {

    public static void main(String[] args) {

        var bookBank = new HashMap<String,Client>();
        StringBuilder alphabet = new StringBuilder("qwertyuiopasdfghjklzxcvbnm");

        createBookBank(alphabet, bookBank);
        Bank bank = new Bank(bookBank);
        System.out.println(bank.allMoney());

        thousandRandomTransactions(bookBank, bank);

        System.out.println(bank.allMoney());
        System.out.println(bank.getBlackList().size());
    }

    private static void thousandRandomTransactions(HashMap<String, Client> bookBank, Bank bank) {
        for (int i = 0; i < 1000; i++) {
            HashSet<String> mySet = new HashSet<>(bookBank.keySet());
            bank.transfer(randomClient(mySet), randomClient(mySet),
                    RandomGenerator.getDefault().nextLong(51000));
        }

    }

    public static String randomClient(HashSet<String> set){
        int randomIndex = new Random().nextInt(set.size());
        int i = 0;
        for (String element : set) {
            if (i == randomIndex) {
                return element;
            }
            i++;
        }
        return null;
    }

    public void randomTransaction(String client1, String client2){

    }

    private static void createBookBank(StringBuilder alphabet, HashMap<String, Client> bookBank) {
        for (int i = 0; i < 1000; i++) {
            StringBuilder name = new StringBuilder();
            for (int j = 0; j < 6; j++) {
                name.append( alphabet.charAt( (int) (Math.random()*10) ) );
            }
            Client client = new Client(name.toString(), (long) (Math.random()*100000) );
            bookBank.put(client.getId(), client);
        }
    }
}
