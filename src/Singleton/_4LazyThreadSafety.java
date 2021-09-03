package Singleton;

/**
 * Sometimes we only want the instance be created when the getInstance() is called instead of the static context
 *
 * To prevent multiple threads access the getInstance() method at the same time, use Synchronize
 */

class LazySingleton{

    private  static LazySingleton instance;

    private LazySingleton(){
        System.out.println("initializing a lazy instance");
    }

//    public static LazySingleton getInstance(){
//        if(instance==null){
//            instance=new LazySingleton();
//        }
//        return instance;
//    }

    //double-check lock(already outdated)
    public  static LazySingleton getInstance(){
        if (instance==null){
            synchronized (LazySingleton.class){
                if(instance==null){
                    instance=  new LazySingleton();
                }
            }
        }
        return instance;
    }


}

public class _4LazyThreadSafety {

}
