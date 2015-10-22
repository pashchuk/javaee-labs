import utils.DbInitializer;

/**
 * Created by mamax on 10/1/2015.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Program started!");

        DbInitializer.createDbIfNotExist();
    }
}
