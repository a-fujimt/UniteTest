package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.SourceWriter;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class UniteTestMain {

    @Argument(required = true, metaVar = "directory containing tests")
    private String path;

    @Option(name = "-o", aliases = "--output", metaVar = "output test name")
    private String filename;

    @Argument(index = 1, handler = StringArrayOptionHandler.class, metaVar = "unite file keywords")
    private String[] options;

    @Option(name = "-h", aliases = "--help")
    private boolean showUsage;

    public static void main(String[] args) throws IOException, CmdLineException {
        new UniteTestMain().run(args);
    }

    public void run(String path, String filename, String[] keywords) throws IOException {
        final TreeManager treeManager = keywords == null ?
                new TreeManager(path) : new TreeManager(path, keywords);
        final ASTNode astNode = treeManager.unite(filename);
        final String testPath = filename != null ?
                filename : treeManager.getFileEntities().get(0).getPath();
        SourceWriter.write(path, testPath, astNode);

        System.out.println("Done");
    }

    void run(String[] args) throws CmdLineException, IOException {
        parseArguments(args);
        if (showUsage) {
            System.out.println("usage:");
            new CmdLineParser(this).printUsage(System.out);
            return;
        }

        run(this.path, this.filename, this.options);
    }

    void parseArguments(String[] args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
    }

    String getPath() {
        return path;
    }

    String getFilename() {
        return filename;
    }

    String[] getOptions() {
        return options;
    }

}
