import java.io.StringReader;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.Tree;

public class Test {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        System.out.println("Processing...");
        CoffeeScriptLexer lexer = new CoffeeScriptLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CoffeeScriptParser parser = new CoffeeScriptParser(tokens);
        CoffeeScriptParser.root_return res = parser.root();
        
        Tree tree = (Tree)res.getTree(); // The root node.
        print(tree);
    }

	public static void print(Tree node) {
    System.out.println(node.toStringTree());
    //print(node, "");
	}

	public static void print(Tree node, String indent) {
    System.out.println(indent + nodeString(node));
		int count = node.getChildCount();
		for (int i=0;i<count;i++) {
			Tree child = node.getChild(i);
			print(child, indent + "  ");
		}
	}

  public static String nodeString(Tree node) {
    return node.toString();
  }
}
