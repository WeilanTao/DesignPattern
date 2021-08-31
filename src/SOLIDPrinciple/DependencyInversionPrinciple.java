package SolidDesignPrinciple;

import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @File DependencyInversionPrinciple.java
 * @Author Emily Weilan Tao
 * @Date 2021-08-31
 * @Description DependencyInversionPrinciple ->
 * A. High-level modules should not depend on low-level modules
 * Both should depend on abstractions
 * <p>
 * B. Abstractions(abstract class or interface) should not depend on details
 * Details should depend on abstractions. [If you can, use interfaces and abstract classes instead of concrete classes,
 * because you can substitute one implementation for another without breaking anything]
 * @Since version-1.0
 * @Copyright Copyright (c) 2021
 */

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }
}

/**
 * This is low-level module because it's related to data storage (as it just manages a list)
 */
class Relationships implements RelationshipBrowser {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

/**
 * High-level module as it performs operations on low level constructs
 * It is closer to the end user
 */
class Research {
    public Research(RelationshipBrowser relationshipBrowser) {
        //This is bad. This is violating the Dependency Inversion Principle as it's taking a low level module as a dependency
//        List<Triplet<Person, Relationship, Person>> relations= relationship.getRelations();

        List<Person> children = relationshipBrowser.findAllChildrenOf("John");

        for(Person p : children){
            System.out.println("John has a child called "+ p.name);
        }

    }
}

public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        Research research = new Research(relationships);
    }
}
