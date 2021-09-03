package Singleton;

/**
 * In this case, users can make many instances of the class CEO; but everything maps to
 * just a pair of static fields of name and age
 *
 * Monostate works if you want to make an instance as after thought into a system where the constructor has been called
 */

class CEO {
    private static String name;
    private static int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        CEO.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        CEO.age = age;
    }

    @Override
    public String toString() {
        return "CEO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class _7Monostate {
    public static void main(String[] args) {
        CEO ceo = new CEO();
        ceo.setName("Alice");
        ceo.setAge(55);

        CEO ceo1 = new CEO();
        System.out.println(ceo1);
    }
}
