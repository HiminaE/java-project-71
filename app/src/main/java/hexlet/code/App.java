package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.IOException;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0.0",
        description = "Comparing the contents of two files.")

public final class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "path1", description = "patch to first file")
    private String path1;
    @Parameters(index = "1", paramLabel = "path2", description = "patch to second file")
    private String path2;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish", paramLabel = "format", description = "output format [default: stylish]")
    private String formatter;

    @Override
    public Integer call() throws IOException {
        System.out.println("filepath1" + path1);
        System.out.println("filepath2" + path2);
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine((new App())).execute(args);
        System.exit((exitCode));
    }
}
