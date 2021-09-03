package Singleton;

import java.io.*;

/**
 * This singleton doesn't have the problems of Reflection
 *
 * Enums are serializable by default; but this serialization won't let you preserve the state of the singleton;
 * As you serialize an enum, only the name of the enum is serialized
 *
 * So with enum, you can get the name of the enum serialized; but if you have any other fields, they won't get serialized
 * also, you can't inherit from the enum
 */
enum EnumBasedSingleton{
    INSTANCE;

    /**
     * Usually, enum already have a private default constructor
     *
     * The constructor for enum is always private no matter what
     */
    EnumBasedSingleton() {
        value = 42;
    }

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

public class _6EnumBasedSingleton {

    static void saveToFile(EnumBasedSingleton singleton,
                           String filename) throws Exception
    {
        try(FileOutputStream fileout=new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileout)){
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename)throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (EnumBasedSingleton)in.readObject();
        }
    }
    public static void main(String[] args) throws Exception {
        String file= "myfile.bin";
//        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
//        singleton.setValue(1111);
//        saveToFile(singleton,file);

        EnumBasedSingleton singleton1 = readFromFile(file);
        System.out.println(singleton1.getValue());

    }
}
