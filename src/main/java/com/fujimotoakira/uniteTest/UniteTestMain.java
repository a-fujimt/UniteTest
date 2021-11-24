package com.fujimotoakira.uniteTest;

import com.fujimotoakira.uniteTest.io.SourceWriter;
import java.util.Arrays;
import org.eclipse.jdt.core.dom.ASTNode;

import java.io.IOException;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

public class UniteTestMain {

    @Argument
    private String path;

    @Option(name="-o", aliases="--output")
    private String filename;

    @Argument(index=1, handler=StringArrayOptionHandler.class)
    private String[] options;

    public static void main(String[] args) throws IOException, CmdLineException {
        new UniteTestMain().run(args);
    }

    public void run(String path, String filename, String[] keywords) throws IOException {
        final TreeManager treeManager = keywords == null ?
                new TreeManager(path) : new TreeManager(path, keywords);
        final ASTNode astNode = treeManager.unite();
        final String testPath = filename != null ?
                filename : treeManager.getFileEntities().get(0).getPath();
        SourceWriter.write(path, testPath, astNode);

        System.out.println("Done");
    }

    void run(String[] args) throws CmdLineException, IOException {
        parseArguments(args);
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
