

public class UserIdsGenerator {
    private int id  = -1;
    private static UserIdsGenerator INSTANCE = null;
    
    public static UserIdsGenerator generateId(){
        if (INSTANCE == null)
            INSTANCE = new UserIdsGenerator();
        return INSTANCE;
    }

    public int getId() {
        return ++id;
    }
}
