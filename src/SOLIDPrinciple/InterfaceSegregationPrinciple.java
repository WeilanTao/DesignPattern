package SOLIDPrinciple;

/**
 * @File InterfaceSegregationPrinciple.java
 * @Author Emily Weilan Tao
 * @Date 2021-08-31
 * @Description InterfaceSegregationPrinciple-> It is about how to split interfaces into smaller interfaces.
 * @Since version-1.0
 * @Copyright Copyright (c) 2021
 */

class Document {

}

interface Machine {
    void print(Document d);

    void fax(Document d);

    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

/**
 * In OlOldFashionPrinter we don't want to implement fax and scan
 * <p>
 * But we can't just leave the method empty;
 * We can't just throw an Exception since it will force all the fax and scan methods to throw Exception; in which case we may not own the other interfaces and the parent class
 */
class OldFashionPrinter implements Machine {

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}


/**
 * To solve the problem above, we separate the interfaces:
 * YAGNI (You Ain't Going to Need it) -> We don't have to implement all the methods
 */
interface Printer {
    void print(Document d);
}

interface Scanner {
    void scan(Document d);
}

class JustPrinter implements Printer {

    @Override
    public void print(Document d) {
    }
}

class Photocopier implements Printer, Scanner {

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultifunctionDevice extends Printer, Scanner { //interface 的本质是抽象类
}

class MultiFunctionMachine implements  MultifunctionDevice{

    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

public class InterfaceSegregationPrinciple {
}
