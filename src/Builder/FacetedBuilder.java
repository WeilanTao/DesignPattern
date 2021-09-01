package Builder;

/**
 * We will have a builder for address and one for employment
 */

class FacetedPerson{
    //address
    public String streetAddress, postcode, city;

    //employment
    public String companyName, position;
    public int annualIncome;


    @Override
    public String toString() {
        return "FacetedPerson{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

//Builder Facade
class FacetedPersonBuilder{
    protected FacetedPerson person = new FacetedPerson();

    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(person);
    }

    public FacetedPerson build(){
        return person;
    }
}

class PersonAddressBuilder extends FacetedPersonBuilder{
    public  PersonAddressBuilder(FacetedPerson person){
        this.person = person;
    }

    public PersonAddressBuilder at(String streetAddress){
        person.streetAddress=streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode){
        person.postcode= postcode;
        return this;
    }

    public PersonAddressBuilder in(String city){
        person.city=city;
        return this;
    }
}

class PersonJobBuilder extends FacetedPersonBuilder
{
    public PersonJobBuilder(FacetedPerson person)
    {
        this.person = person;
    }

    public PersonJobBuilder at(String companyName)
    {
        person.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position)
    {
        person.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome)
    {
        person.annualIncome = annualIncome;
        return this;
    }
}



public class FacetedBuilder {
    public static void main(String[] args) {
        FacetedPersonBuilder pb = new FacetedPersonBuilder();
        FacetedPerson p = pb.lives()
                    .at("123 road")
                    .in("London")
                    .withPostcode("1d1 3e3")
                    .works()
                    .at("New York")
                    .asA("Engineer")
                    .earning(123000)
                    .build();

        System.out.println(p);

    }
}
