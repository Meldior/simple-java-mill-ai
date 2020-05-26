package profiling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class TimeChecker {
    String algorithm;
    private long start, end;
    long iterations;
    List<Long> elapsedTimes = new ArrayList<>();
    List<Long> iterationsRequired = new ArrayList<>();

    public TimeChecker(String algorithm){
        this.algorithm = algorithm;
        clear();
    }

    public void clear(){
        start = 0L;
        end = 0L;
        iterations = 0L;
    }

    public void start(){
        start = System.nanoTime();
    }

    public void end(){
        end = System.nanoTime();
        elapsedTimes.add(getElapsedTime());
        iterationsRequired.add(iterations);
    }

    public long getElapsedTime(){
        if(end != 0)
            return end - start;
        else
            return System.nanoTime() - start;
    }

    public void tick(){
        iterations++;
    }

    public long getIterations() {
        return iterations;
    }

    private BigDecimal getAvgIterations(){
        BigDecimal sum = new BigDecimal(0);
        for(Long l: iterationsRequired) {
            sum = sum.add(new BigDecimal(l));
        }
        return sum.divide(new BigDecimal(iterationsRequired.size()), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal getAvgTime(){
        BigDecimal sum = new BigDecimal(0);
        for(Long l: elapsedTimes) {
            sum = sum.add(new BigDecimal(l));
        }
        return sum.divide(new BigDecimal(elapsedTimes.size()), 2, RoundingMode.HALF_UP);
    }

    public void logToFile(String filename){
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(new File(filename)));

            fw.write("average time taken for: " + algorithm + " " + getAvgTime() + "\n");
            fw.write("average iterations taken for: " + algorithm + " " + getAvgIterations());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
