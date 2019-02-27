package db;

public class NfvDB {

    private static NfvDB nfvDB = new NfvDB();

    public static NfvDB getNfvDB(){
        return nfvDB;
    }
}
