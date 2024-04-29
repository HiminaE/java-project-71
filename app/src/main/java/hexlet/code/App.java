package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;
import java.io.IOException;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {

    @Parameters(index = "0", paramLabel = "filepath1", description = "patch to first file")
    private String filepath1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "patch to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws IOException {
        System.out.println("filepath1" + filepath1);
        System.out.println("filepath2" + filepath2);
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine((new App())).execute(args);
        System.exit((exitCode));
    }
}
