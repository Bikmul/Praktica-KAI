

public class UsersArrayList {
    private User[] array = new User[10];
    private int amount  = -1;

    public void add(User user) {
        if (amount >= array.length-1) {
            User[] newarray = new User[array.length*2];
            for(int i=0; i < array.length; i++){
                newarray[i] = array[i];
            }
            array = newarray;
        }
        amount++;
        array[amount] = user;
    }
    public User findbyid(int id) throws Exception {
        if (amount >= id) {
            for (User user : array){
            if(user.getid() == id)
                return user;
            }
        }
        throw new Exception("User not found");
    }
    public User findbyindex(int index) throws Exception {
        if(amount >= index)
            return array[index];
        throw (new Exception("User not found"));
    }
    public int getamount(){
        return ++amount;
    }  
    public void printer(){
        System.out.printf("%-8s", "USER ");
        System.out.printf("|");
        System.out.printf("%-8s", "ID");
        System.out.printf("|");
        System.out.printf("%-8s", "Money");
        System.out.printf("|");
        System.out.println();
        for(int i=0; i < array.length; i++){
            if(array[i] != null){
                User n = array[i];
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
}