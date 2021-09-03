package Singleton;

class BasicSingleton {
    private BasicSingleton(){
    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getINSTANCE() {
        return INSTANCE;
    }

    private int value=0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}


public class _1BasicSingletonDemo{
    public static void main(String[] args) {
        BasicSingleton singleton =  BasicSingleton.getINSTANCE();

        singleton.setValue(123);
        System.out.println(singleton.getValue());

    }
}
