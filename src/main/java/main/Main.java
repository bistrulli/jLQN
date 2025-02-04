package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

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
		// Find the current path (run the jar file from the root of the project)
		
		String projectRoot = System.getProperty("user.dir");
		String directoryPath = projectRoot + "/resources/wasteless_journal";
		String wildcardPattern = "*.lqn";
		try {
			Collection<File> matchingFiles = FileUtils.listFiles(new File(directoryPath),
					new WildcardFileFilter(wildcardPattern), null);
			if (matchingFiles.isEmpty()) {
				System.out.println("No files found matching the pattern '" + wildcardPattern + "' in directory '"
						+ directoryPath + "'");
			} else {
				System.out.println("Matching files:");
				for (File f : matchingFiles) {
					System.out.println(f.getName());
					/*
					 * get the input file as an InputStream
					 */
					// File f = new File("src/main/resources/case_studies/sockshop.lqn");
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
					//LqnToMPP mpptrasducer = new LqnToMPP(myListner.app);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}