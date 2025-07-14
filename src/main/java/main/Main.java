package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import JLQN.JLqnLexer;
import JLQN.JLqnParser;
//import com.khubla.antlr4example.Cobol85Parser.StartRuleContext;
import JLQN.JLqnParser.RuleModelContext;
import JLQNListner.MyJqnListner;
//import LQN2MPP.LqnToMPP;
import LqnToGCF.LqnToGcf;

//import main.Config;

/**
 * Author: Tom Everett
 */
public class Main { 
	
    static public Path projectPath;
    static public Config config;
        
    public static void main(String[] args) {
        config = processCommandLineArgs(args);
		Path currentFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toAbsolutePath();
        projectPath = currentFilePath.getParent().getParent(); 
        // Find the current path (run the jar file from the root of the project)
        
        Path directoryPath = projectPath.resolve(config.getLqnModelPath());
        String wildcardPattern = "*.lqn";
        try {
            Collection<File> matchingFiles = FileUtils.listFiles(directoryPath.toFile(),
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

    private static Config processCommandLineArgs(String[] args) {
        Options options = new Options();

        // Project, region and prometheus-ip options (useful for the configuration of the resulting application)
        Option projectName = new Option(
            "p",
            "project",
            true,
            "Project name (if not provided, the resulting code will retrieve the PROJECT_NAME environment variable at runtime)"
        );
        projectName.setRequired(false);
        options.addOption(projectName);

        Option regionName = new Option(
            "r",
            "region",
            true,
            "Region name (if not provided, the resulting code will retrieve the REGION_NAME environment variable at runtime)"
        );
        regionName.setRequired(false);
        options.addOption(regionName);

        Option prometheusOption = new Option(
            "pi",
            "prometheus-ip",
            true,
            "The IP address of the Prometheus server (if not provided, the resulting code will retrieve the PROMETHEUS_IP environment variable at runtime)"
        );
        prometheusOption.setRequired(false);
        options.addOption(prometheusOption);

        // Input and output paths
        Option lqnPathOpt = new Option("l", "lqnPath", true, "LQN models Directory (default: <projectRoot>/resources/wasteless_journal)");
        lqnPathOpt.setRequired(false);
        options.addOption(lqnPathOpt);

        Option outputDirOption = new Option("o", "output", true, "Output directory for generated files (default: <projectRoot>/output)");
        outputDirOption.setRequired(false);
        options.addOption(outputDirOption);

        // Testing and debugging options
        Option testOption = new Option("t", "test", false, "Local test mode");
        testOption.setRequired(false);
        options.addOption(testOption);

        Option sleepOption = new Option("s", "sleep", false, "Sleep mode");
        sleepOption.setRequired(false);
        options.addOption(sleepOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            String project = cmd.getOptionValue("project");
            String region = cmd.getOptionValue("region");
            boolean isTest = cmd.hasOption("test");
            boolean isSleep = cmd.hasOption("sleep");
            String prometheusIp = cmd.getOptionValue("prometheus-ip");

            // Default lqnPath to "<projectRoot>/resources/wasteless_journal" if not provided
            String lqnPath = cmd.getOptionValue("lqnPath");
            if (lqnPath == null) {
                Path currentFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toAbsolutePath();
                Path projectRoot = currentFilePath.getParent().getParent();
                lqnPath = projectRoot.resolve("resources/wasteless_journal").toString();
            }

            // Default output directory to "<projectRoot>/output" if not provided
            String outputDir = cmd.getOptionValue("output");
            if (outputDir == null) {
                Path currentFilePath = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toAbsolutePath();
                Path projectRoot = currentFilePath.getParent().getParent();
                outputDir = projectRoot.resolve("output").toString();
            }

            return new Config(project, region, isTest, isSleep, prometheusIp, lqnPath, outputDir);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        return null;
    }
}