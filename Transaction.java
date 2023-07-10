
import java.util.UUID;

public class Transaction {
    private UUID uuid;
    private User sender;
    private User recipient;
    private int amount;
    private String type;

    public Transaction( User sender, User recipient, int amount,String type) {
        uuid = UUID.randomUUID();
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.type = type;
    }
    public int makeTransaction(){
        if(type.equals("DEBIT")){
            if (sender.getmoney() - amount < 0){
                System.out.println("No balance");
                return 0;
            }
            else{
                recipient.setmoney(recipient.getmoney()+amount);
                sender.setmoney(sender.getmoney()-amount);
                return 1;
            }
        }
        else if(type.equals("KREDIT")){
            if (recipient.getmoney() - amount < 0){
                System.out.println("No balance");
                return 0;
            }
            else{
                sender.setmoney(sender.getmoney()+amount);
                recipient.setmoney(recipient.getmoney() - amount);
                return 1;
            }
        }
        else
            return 0;
    }
    
    public void setUuid(UUID uuid){
        this.uuid = uuid;
    }
    public void setSender(User sender){
        this.sender = sender;
    }
    public void setRecipient(User recipient){
        this.recipient = recipient;
    }
    public void setamount(int amount){
        this.amount = amount;
    }
    public void settype(String type){
        this.type = type;
    }
    public User getSender(){
        return sender;
    }
    public User getRecipient(){
        return recipient;
    }
    public int getamount(){
        return  amount;
    }
    public UUID getUuid(){
        return uuid;
    }
    public String gettype(){
        return type;
    }
    public void printer(String type){
        System.out.printf("%-8s", sender.getname());
        System.out.printf("to ");
        System.out.printf("%-8s", recipient.getname());
        System.out.printf("|");
        System.out.printf("%-8s", type);  
        System.out.printf("|");
        System.out.printf("%-8s", amount);      
        System.out.println();
    }
}
