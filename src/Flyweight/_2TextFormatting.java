package Flyweight;


import java.util.ArrayList;
import java.util.List;

class BetterFormattedText{
    private String plainText;
            private List<TextRange> formatting  = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end){
        TextRange textRange = new TextRange(start, end);
        formatting.add(textRange);
        return textRange;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       for(int i =0; i<plainText.length(); i++){
           char c = plainText.charAt(i);
           for(TextRange range: formatting){
               if(range.covers(i) && range.capitalize){
                   c=Character.toUpperCase(c);
               }
               sb.append(c);
           }
       }
       return sb.toString();
    }

    /**
     * The flyweight
     *
     * Here with TextRange, we don't have to waste memory to build a boolean array for every string to be formatted
     */
    public class TextRange{
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end){
            this.start = start;
            this.end = end;
        }

        public  boolean covers(int position){
            return position >= start && position<=end;
        }
    }
}

public class _2TextFormatting {
    public static void main(String[] args) {
        BetterFormattedText betterFormattedText=
                new BetterFormattedText("Make America Great Again");

        betterFormattedText.getRange(13,18).capitalize=true;
        System.out.println(betterFormattedText);



    }
}
