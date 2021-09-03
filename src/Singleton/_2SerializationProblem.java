package Singleton;

import java.io.*;

/**
 * When you deserialize an object, the JVM doesn't care that constructor is private; it's still going to construct an object anyway.
 */

class SerializationSingleton implements Serializable {
    private SerializationSingleton(){
    }

    private static final SerializationSingleton INSTANCE = new SerializationSingleton();

    public static SerializationSingleton getINSTANCE() {
        return INSTANCE;
    }

    private int value=0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Tell JVM that, whenever we have serialization, it has to happen into the instance
     * @return
     */
    protected Object readResolve(){
        return INSTANCE;
    }
}


public class _2SerializationProblem {

    static void saveToFile(SerializationSingleton singleton,
                           String filename) throws Exception
    {
        try( FileOutputStream fileout=new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(singleton);
        }
    }

    static SerializationSingleton readFromFile(String filename)throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (SerializationSingleton)in.readObject();
        }
    }

    public static void main(String[] args) throws  Exception {
        SerializationSingleton singleton =  SerializationSingleton.getINSTANCE();

        singleton.setValue(111);

        String file = "singleton.bin";
        saveToFile(singleton, file);

        singleton.setValue(222);

        SerializationSingleton singleton1 = readFromFile(file);

        System.out.println(singleton==singleton1);

        System.out.println(singleton.getValue());
        System.out.println(singleton1.getValue());
    }
}
