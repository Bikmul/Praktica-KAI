

public class UsersList {
    private User[] storage = new User[10];
    private int amount  = -1;

    public void add(User user) {
        if (amount >= storage.length-1) {
            User[] newstorage = new User[storage.length*2];
            for(int i=0; i < storage.length; i++){
                newstorage[i] = storage[i];
            }
            storage = newstorage;
        }
        amount++;
        storage[amount] = user;
    }
    public User findbyid(int id) throws Exception {
        if (amount >= id) {
            for (User user : storage){
            if(user.getid() == id)
                return user;
            }
        }
        throw new Exception("User not found");
    }
    public User findbyindex(int index) throws Exception {
        if(amount >= index)
            return storage[index];
        throw (new Exception("User not found"));
    }
    public int getamount(){
        return ++amount;
    }  
    public void printer() {
        System.out.println("===========================");
        System.out.printf("%-8s", "USER ");
        System.out.printf("|");
        System.out.printf("%-8s", "ID");
        System.out.printf("|");
        System.out.printf("%-8s", "Money");
        System.out.printf("|");
        System.out.println("");
        if (storage.length != 0){
            for(int i=0; i < storage.length; i++){
                if(storage[i] != null){
                    User n = storage[i];
                    System.out.printf("%-8s", n.getname());
                    System.out.printf("|");
                    System.out.printf("%-8s", n.getid());
                    System.out.printf("|");
                    System.out.printf("%-8s", n.getmoney());
                    System.out.printf("|");
                    System.out.println();
                }
            }
        }
        System.out.println("===========================");
    }
}