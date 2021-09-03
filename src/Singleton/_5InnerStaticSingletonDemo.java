package Singleton;

/**
 * A reduction of LazyThreadSafeSingleton
 */

class InnerStaticSingleton{
    private InnerStaticSingleton(){}

    /**
     * This approach avoids the problem of synchronization
     *
     * It guarantees that only one instance is created whenever you initialize the instance
     * and there is no need to take care of the thread safety
     */
    private static class Impl{
        private static final InnerStaticSingleton INSTANCE= new InnerStaticSingleton();
    }

    public InnerStaticSingleton getInstance(){
        return Impl.INSTANCE;
    }
}

public class _5InnerStaticSingletonDemo {
}
