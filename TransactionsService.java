

public class TransactionsService {
    private UsersArrayList personlist = new UsersArrayList();
    private TransactionsLinkedList Fulllist = new TransactionsLinkedList();

    TransactionsService() {}

    public void addUser(String name, int money){
        User newUser = new User(name,money);
        personlist.add(newUser);
    }
    public int getUserBalance(int userid) throws Exception {
        return personlist.findbyid(userid).getmoney();
    }
    public void makeTransaction(int userid1,int userid2, int amount, String type) throws Exception{
        String s1 = "DEBIT";
        String s2 = "KREDIT";
        if(type.equals(s1)) {
            Transaction Transactionofdebit = new Transaction(personlist.findbyid(userid1),personlist.findbyid(userid2),amount,s1);
            Transaction Transactionofcredit = new Transaction(personlist.findbyid(userid2),personlist.findbyid(userid1),amount,s2);
            Transactionofdebit.makeTransaction();
            personlist.findbyid(userid1).addtoList(Transactionofdebit);
            personlist.findbyid(userid2).addtoList(Transactionofcredit);
            Fulllist.add(Transactionofcredit);
            Fulllist.add(Transactionofdebit);
        }
        if (type.equals(s2)) {
            Transaction Transactionofdebit = new Transaction(personlist.findbyid(userid1),personlist.findbyid(userid2),amount,s2);
            Transaction Transactionofcredit = new Transaction(personlist.findbyid(userid2),personlist.findbyid(userid1),amount,s1);
            Transactionofdebit.makeTransaction();
            personlist.findbyid(userid1).addtoList(Transactionofdebit);
            personlist.findbyid(userid2).addtoList(Transactionofcredit);
            Fulllist.add(Transactionofcredit);
            Fulllist.add(Transactionofdebit);
        }
    }
    public void printallUsers(){
        personlist.printer();
    }
    public void printallTransactions(){
        Fulllist.printer();
    }
    public void getUserTransactions(int userid) throws Exception{
        personlist.findbyid(userid).printlistofTrans();
    }
    public User getuserbyid(int userid) throws Exception{
        return personlist.findbyid(userid);
    }
    public TransactionsLinkedList getTL(){
        return Fulllist;
    }
}
