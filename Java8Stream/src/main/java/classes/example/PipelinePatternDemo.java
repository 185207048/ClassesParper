package classes.example;

import java.util.function.Function;

public class PipelinePatternDemo {
    public static void main(String[] args) {
        Function<String, String> printline = removePunctuation().andThen(removeWhitespace()).andThen(convertToLowercase());
        String result = printline.apply("Hello world");
        System.out.println(result);
    }

    private static Function<String, String> removePunctuation(){
        return str -> str.replace("\\p{Punct}", "");
    }

    private static Function<String, String> removeWhitespace() {
        return str -> str.replaceAll("\\s", "");
    }

    private static Function<String, String> convertToLowercase() {
        return String::toLowerCase;
    }


}
