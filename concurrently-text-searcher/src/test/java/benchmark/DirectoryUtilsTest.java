package benchmark;

import com.deepspace.FileReverseRewriterType;
import com.deepspace.ResultProcessType;
import com.deepspace.RewriteUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(DirectoryUtilsTest.N)
public class DirectoryUtilsTest {

    public static final int N = 2;

    @Benchmark
    public void idk() {
        String path = "concurrently-text-searcher/src/main/resources/files";
        String[] fileNames = {path, "1.txt", "2.txt", "3.txt", "4.txt", "5.txt", "6.txt", "7.txt", "8.txt", "9.txt", "10.txt", "11.txt"};
        RewriteUtils rewriteUtils = new RewriteUtils();
        rewriteUtils.checkOnExistenceAndStart(fileNames, 10, ResultProcessType.GENERAL_TIME, FileReverseRewriterType.MONO, Boolean.FALSE);
    }
}
