package Factory;

import org.javatuples.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

interface IHotDrink {
    void consume();
}

class Tea implements IHotDrink {
    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}

class Coffee implements IHotDrink {

    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

/**
 * Make a corresponding hierarchy of factories which would construct those objects
 */
interface IHotDrinkFactory {
    IHotDrink prepare(int amount);
}

class TeaFactory implements IHotDrinkFactory {

    @Override
    public IHotDrink prepare(int amount) {
        System.out.println("Put in tea bag, pour "
                + amount + "ml, add lemon, enjoy!");
        return new Tea();
    }
}

class CoffeeFactory implements IHotDrinkFactory {

    @Override
    public IHotDrink prepare(int amount) {
        System.out.println("Grind some beans, boil water, pour"
                + amount + "ml, add cream and sugar, enjoy!");

        return new Coffee();
    }
}

class HotDrinkMachine {
    private List<Pair<String, IHotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       Set<Class<? extends IHotDrinkFactory>> types =
               new Reflections("factory").getSubTypesOf(IHotDrinkFactory.class);

       for(Class<? extends IHotDrinkFactory> type: types){
           namedFactories.add(
                   new Pair<>(
                           type.getSimpleName().replace("Factory",""),
                           type.getDeclaredConstructor().newInstance()));
       }

    }

    public IHotDrink makeDrink() throws Exception{
        System.out.println("Available drinks:");
        for(int index=0; index<namedFactories.size(); index++){
            Pair<String, IHotDrinkFactory> item =  namedFactories.get(index);
            System.out.println(""+index+": "+item.getValue0());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s;
            int i, amount;
            if(((s= reader.readLine())!=null)
                    && ((i=Integer.parseInt(s))>=0)
                    && i<namedFactories.size()){
                System.out.println("Specify amount: ");
                s= reader.readLine();
                if(s!=null && (amount=Integer.parseInt(s))>0){
                    return namedFactories.get(i).getValue1().prepare(amount);
                }
            }
            System.out.println("incorrect input, try again.");
        }
    }
}

public class AbstractFactory {
    public static void main(String[] args) throws Exception {
        HotDrinkMachine machine= new HotDrinkMachine();

        IHotDrink drink = machine.makeDrink();
        drink.consume();
    }
}
