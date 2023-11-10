package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import JLQN.JLqnLexer;
import JLQN.JLqnParser;
//import com.khubla.antlr4example.Cobol85Parser.StartRuleContext;
import JLQN.JLqnParser.RuleModelContext;
import JLQNListner.MyJqnListner;
import LQN2MPP.LqnToMPP;
import LqnToGCF.LqnToGcf;

/**
 * @author Tom Everett
 */
class Main {
	public static void main(String[] args) {
		System.out.println("Antlr4 Example");
		try {
			/*
			 * get the input file as an InputStream
			 */
			File f=new File("src/main/resources/case_studies/illustrative.lqn");
			InputStream inputStream = new FileInputStream(f);
			/*
			 * make Lexer
			 */
			Lexer lexer = new JLqnLexer(CharStreams.fromStream(inputStream));
			/*
			 * get a TokenStream on the Lexer
			 */
			TokenStream tokenStream = new CommonTokenStream(lexer);
			/*
			 * make a Parser on the token stream
			 */
			JLqnParser parser = new JLqnParser(tokenStream);
			MyJqnListner myListner = new MyJqnListner();
			parser.addParseListener(myListner);
			/*
			 * get the top node of the AST. This corresponds to the topmost rule of
			 * equation.q4, "equation"
			 */
			@SuppressWarnings("unused")
			RuleModelContext ruleModel = parser.ruleModel();
			
			LqnToGcf gcftrasducer = new LqnToGcf(myListner.app);
			LqnToMPP mpptrasducer = new LqnToMPP(myListner.app);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}