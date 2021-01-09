import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    private static void print(String str){
        ParserLexer lexer = new ParserLexer(CharStreams.fromString(str));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ParserParser parser = new ParserParser(tokens);
        System.out.println(parser.expression().expr);
    }

    public static void main(String[] args){
        String str = sc.nextLine();
        print(str);
    }
}
