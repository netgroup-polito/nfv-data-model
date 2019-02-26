import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NfvDB {

    private static NfvDB nfvDB = new NfvDB();

    public static NfvDB getNfvDB(){
        return nfvDB;
    }
}
