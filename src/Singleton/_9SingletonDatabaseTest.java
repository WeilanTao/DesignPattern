package Singleton;

import com.google.common.collect.Iterables;
import org.junit.jupiter.api.Test;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;



class SingletonDatabase implements Database{
    private Dictionary<String, Integer> capitals = new Hashtable<>();
    private static int instanceCount = 0;

    public static int getInstanceCount() {
        return instanceCount;
    }

    private  SingletonDatabase(){
        instanceCount++;
        System.out.println("Initializing database");

        try{
            File file = new File(SingletonDatabase.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath());
            Path path = Paths.get(file.getPath(),"capitals.txt");
            List<String> lines = Files.readAllLines(path);

//            Iterables.partition(lines, 2)
//                    .forEach(kv->capitals.put(
//                            kv.get(0).trim(),
//                            Integer.parseInt(kv.get(1))
//                    ));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance(){
        return INSTANCE;
    }

    public int getPopulation(String name){
        return capitals.get(name);
    }
}

class SingleronRecordFinder{
    public int getTotalPopulation(List<String> names){
        int result = 0;
        for (String name : names)
            result += SingletonDatabase.getInstance().getPopulation(name);
        return result;
    }
}

//Dependency Injection/////////////////////////////////////////////////////////////////////////////////////////////////

interface Database
{
    int getPopulation(String name);
}
class DummyDatabase implements Database
{
    private Dictionary<String, Integer> data = new Hashtable<>();

    public DummyDatabase() {
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name)
    {
        return data.get(name);
    }
}

class ConfigurableRecordFinder
{
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names)
            result += database.getPopulation(name);
        return result;
    }
}

public class _9SingletonDatabaseTest {
}



class Testability
{
    @Test
    public void isSingletonTest()
    {
        SingletonDatabase db = SingletonDatabase.getInstance();
        SingletonDatabase db2 = SingletonDatabase.getInstance();
        assertSame(db, db2);
        assertEquals(1, SingletonDatabase.getInstanceCount());
    }

    @Test//integration test
    public void singletonTotalPopulationTest()
    {
        // testing on a live database
        SingleronRecordFinder rf = new SingleronRecordFinder();
        List<String> names = Arrays.asList("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(176000+1740000, tp);
    }

    @Test
    public void dependentPopulationTest()
    {
        DummyDatabase db = new DummyDatabase();
        ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(
                Arrays.asList("alpha", "gamma")
        ));
    }
}
