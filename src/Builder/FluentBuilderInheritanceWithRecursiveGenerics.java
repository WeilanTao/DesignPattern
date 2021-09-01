package Builder;

class Person
{
    public String name;
    public String position;

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

//The PersonBuilder is going to take a type argument; but the type argument has to be some type of of inheritor of EmployeeBuilder
class PersonBuilder<SELF extends  PersonBuilder<SELF>>{
    protected Person person= new Person();

    public SELF withName(String name){
        person.name = name;
        return self();
    }

    public Person build(){
        return person;
    }

    protected  SELF self(){
        return (SELF) this;
    }

}
class EmployeeBuilder extends  PersonBuilder<EmployeeBuilder>{
    public EmployeeBuilder worksAt(String position){
        person.position=position;
        return self();
    }

    @Override
    protected EmployeeBuilder self() {
        return this;
    }
}

public class FluentBuilderInheritanceWithRecursiveGenerics {
    public static void main(String[] args) {
        EmployeeBuilder personBuilder= new EmployeeBuilder();
        //Here we can't call worksAt()
        //Even if the personBuilder is implemented as a EmployeeBuilder; the works at is returning PersonBuilder!
        personBuilder.withName("Carl").worksAt("Developer").build();

        System.out.println(personBuilder);
    }
}
