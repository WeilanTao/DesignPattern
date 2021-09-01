package Builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HtmlElement {
    public String name, text;
    public List<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        sb.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies(indentSize * (indent + 1), " ")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement e : elements)
            sb.append(e.toStringImpl(indent + 1));

        sb.append(String.format("%s</%s>%s", i, name, newLine));
        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    /**
     * not fluent
     */
    public void addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
    }

    /**
     * fluent
     */
    public HtmlBuilder addChildFluent(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);
        return this;
    }
    public void clear()
    {
        root = new HtmlElement();
        root.name = rootName;
    }

    // delegating
    @Override
    public String toString()
    {
        return root.toString();
    }
}

public class HtmlBuilderSample {
    public static void main(String[] args) {
        HtmlBuilder builder1 = new HtmlBuilder("ul");
        builder1.addChild("li", "hello");
        builder1.addChild("li", "world");
        System.out.println(builder1);


        //fluent interface
        HtmlBuilder builder2 = new HtmlBuilder("ul");
        builder2.addChildFluent("li", "hello").addChild("li", "world");;
        System.out.println(builder2);

    }
}
