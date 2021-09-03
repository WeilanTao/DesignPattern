package Singleton;

import java.io.File;
import java.io.IOException;

/**
 * Static block to solve exception in the Single constructor
 */

class StaticBlockSingleton{
    private  StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        File.createTempFile(".",".");
    }

    private static StaticBlockSingleton instance;

    static {
        try{
            instance= new StaticBlockSingleton();
        }catch (Exception e){
            System.out.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}

public class _3StaticBlockSingletonDemo {

}
