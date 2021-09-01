package Builder;

import java.util.*;

class CodeBuilder {
    public String name;
    public Map<String, String> fields = new HashMap<>();

    public CodeBuilder(String className) {
        name = className;
    }

    public CodeBuilder addField(String name, String type) {
        fields.put(name, type);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + name + "\n" + "{" + "\n");
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            sb.append("  public " + entry.getValue() + " " + entry.getKey()+";\n");
        }
        sb.append("}");
        return sb.toString();
    }
}


public class Exercise {
    public static void main(String[] args) {
        CodeBuilder cb = new
                CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(cb);
    }
}
