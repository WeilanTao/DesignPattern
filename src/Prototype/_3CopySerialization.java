package Prototype;

import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;
/**
 *
 * With Serializable, we don't have to create the copy constructor for every sub class.
 *
 * When you serialize/deserialize an object, you make a brand new copy; and serialization takes care of serializing the entire object.
 * If an object contains other object, they all get serialized/deserialized; and you get a brand new copy
 *
 */
class Foo implements Serializable {
    public int stuff;
    public String str;

    public Foo(int stuff, String str) {
        this.stuff = stuff;
        this.str = str;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", str='" + str + '\'' +
                '}';
    }
}

public class _3CopySerialization {
    public static void main(String[] args) {
        Foo foo= new Foo(42,"life");
        Foo foo2 = SerializationUtils.roundtrip(foo);

        foo2.str="xyz";
        System.out.println(foo);
        System.out.println(foo2);
    }
}
