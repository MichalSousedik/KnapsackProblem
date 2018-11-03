import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

class CPUTimer {

    private long start;

    private List<Double> measurings;

    CPUTimer() {
        this.measurings = new ArrayList<>();
    }

    void start() {
        start = getCpuTime();
    }

    void stop() {
        long end = getCpuTime();
        double seconds = (double) (end - start) / 1000000000.0;
        measurings.add(seconds);
    }

    private double getAverageTime() {
        OptionalDouble average = measurings.stream()
                .mapToDouble(a -> a)
                .average();
        if (average.isPresent())
            return average.getAsDouble();
        return Double.NaN;
    }

    void writeAverageTime() throws IOException {
        String time = String.format ("%.5f", getAverageTime());
        FileWriter fw = new FileWriter("data/time/averageTimes.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(time);
        bw.close();
    }

    /**
     * Get CPU time in nanoseconds.
     */
    private static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

}