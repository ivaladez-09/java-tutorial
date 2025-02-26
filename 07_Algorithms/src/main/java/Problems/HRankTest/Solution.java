package Problems.HRankTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int packetsCount = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> packets = IntStream.range(0, packetsCount).mapToObj(i -> {
          try {
            return bufferedReader.readLine().replaceAll("\\s+$", "");
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        })
        .map(String::trim)
        .map(Integer::parseInt)
        .collect(toList());

    int channels = Integer.parseInt(bufferedReader.readLine().trim());

    long result = Result.findMaximumQuality(packets, channels);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
