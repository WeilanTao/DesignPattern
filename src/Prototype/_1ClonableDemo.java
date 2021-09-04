package Prototype;

import java.util.Arrays;


/**
 * Cloneable is just a marker interface to let the Object.clone() method know that it must not throw an exception when called.
 * It shows an object can be copied but doesn't suggest deep copy or shallow copy
 *
 * This is one of the most badly-designed parts of Java. Usually, you should prefer using a copy contructor instead of using clone().
 *
 * //deep copy-replicate every data member and copy it over
 */
class Address implements Cloneable {
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    //when overriding a method, we can change from protected to public!
    @Override
    public Object clone() throws CloneNotSupportedException {
        //deep copy-replicate every data member and copy it over
        return new Address(streetName, houseNumber);
    }
}


class Person  implements Cloneable {
    public String[] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(names.clone(), (Address) address.clone());
    }
}

public class _1ClonableDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"john", "smith"}, new Address("London Rd", 1231));

        //We are overwriting john!!!(shallow copy)
        Person alice = john;
        alice.names[0] = "alice";
        alice.address.houseNumber = 1232;

        System.out.println(john);
        System.out.println(alice);

        Person bob = (Person) john.clone();
        bob.names[0]="bob";
        bob.address.houseNumber=1233;
        System.out.println(bob);

    }
}
