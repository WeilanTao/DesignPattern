package Prototype;

class IAddress{
    public String streetName;
    public int houseNumber;

    public IAddress(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    /**
     * Copy Constructor: takes another copy of this particular object
     * @return
     */
    public IAddress(IAddress other){
        this(other.streetName, other.houseNumber);
    }


    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}

class Employee{
    public String name;
    public  IAddress address;

    public Employee(String name, IAddress address) {
        this.name = name;
        this.address = address;
    }

    public Employee(Employee other){
        name = other.name;
        address = new IAddress(other.address);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
public class _2CopyConstructorDemo {
    public static void main(String[] args) {
        Employee alice = new Employee("Alice", new IAddress("London Rd", 123));

        Employee bob = new Employee(alice);
        bob.name="bob";
        bob.address.houseNumber=124;
        System.out.println(alice);
        System.out.println(bob);
    }

}
