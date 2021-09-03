package Singleton;

import java.util.HashMap;

/**
 * There is a finite set of instances, for every single instance, you can implement additional benefits like lazy loading etc.
 *
 * Plus, we want to restrict the number of instances that created; or maybe have those instances associated with some sort of enum...
 */

enum Subsystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

class Printer{
    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("A total of "+instanceCount+" instances has been created");
    }

    private static HashMap<Subsystem, Printer> instances = new HashMap<>();

    public static Printer get(Subsystem ss){
        if(instances.containsKey(ss)){
            return instances.get(ss);
        }
        Printer instance = new Printer();
        instances.put(ss,instance);
        return instance;
    }

}

public class _8Multiton {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux  = Printer.get(Subsystem.AUXILIARY);
        Printer aux1 = Printer.get(Subsystem.AUXILIARY);
    }
}
