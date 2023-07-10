

public class User {
    private int id;
    private String name;
    private int money;
    private TransactionsLinkedList list;

    public User(String name, int money) {
        this.id = UserIdsGenerator.generateId().getId();
        this.name = name;
        this.money = money;
        list = new TransactionsLinkedList();
    }
    public void Printinfo() {
        System.out.println(getid() + " - " + getname() + " - " + getmoney());
    }
    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public int getmoney(){
        return money;
    }
    public void setname(String name){
        this.name = name;
    }
    public void setmoney(int money){
        if(money >= 0)
            this.money = money;
        else
            System.out.println("Illigal argument");
    }
    public TransactionsLinkedList getTL(){
        return list;
    }
    public void setTL(TransactionsLinkedList list){
        this.list = list;
    }
    public void addtoList(Transaction x){
        list.add(x);
    }
    public void printlistofTrans(){
        Transaction[] arr = list.toarray();
        System.out.println("List: =========================== ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].gettype().equals("DEBIT"))
                System.out.println(arr[i].getSender().getname() + " ->" + arr[i].getRecipient().getname() + ": " + arr[i].getamount()+ " " + arr[i].gettype() + " UUid:" + arr[i].getUuid());
            if (arr[i].gettype().equals("KREDIT"))
                System.out.println(arr[i].getSender().getname() + " <-" + arr[i].getRecipient().getname() + ": " + arr[i].getamount()+ " " + arr[i].gettype() +  " UUid:" + arr[i].getUuid());
        }
        System.out.println("================================= ");
    }
}
