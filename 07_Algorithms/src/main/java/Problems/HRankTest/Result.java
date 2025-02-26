package Problems.HRankTest;

import java.util.ArrayList;
import java.util.List;

public class Result {
  /*
   * Complete the 'findMaximumQuality' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY packets
   *  2. INTEGER channels
   */

  public static long findMaximumQuality(List<Integer> packets, int channels) {
    // Write your code here
    if (channels == 0 || packets == null || packets.isEmpty() || channels > packets.size()) {
      return 0;
    }

    long maximumQuality = 0;
    // split the list is different combinations of channels
    List<List<List<Integer>>> combinations = getListOfCombinations(packets, channels);

    /*
    [
        [.   channel 1
            [2], [2,3]
        ],
        [.   channel 2
            [3,4], [4]
        ],
        [.   channel n

        ]
    ]
    */

    // for each combination of lists, get maximumQuality
    for(int i = 0; i < combinations.get(0).size(); i++) {
      long tempMaximumQuality = 0;
      for(int j = 0; j < combinations.size(); j++) {
        tempMaximumQuality += getMedian(combinations.get(j).get(i));
      }

      // compare with global maximumQuality and update if needed
      if (tempMaximumQuality > maximumQuality) {
        maximumQuality = tempMaximumQuality;
      }
    }
    return maximumQuality;
  }

  private static int getMedian(List<Integer> list) {
    if(list.isEmpty()) return 0;

    int length = list.size();
    int median = 0;

    if (length % 2 == 0) {
      median = (list.get((length/2) - 1) + list.get((length/2))) / 2;
    } else {
      median = list.get((length/2));
    }

    System.out.println("median " + median + " of list " + list);
    return median;
  }

  private static List<List<List<Integer>>> getListOfCombinations(List<Integer> packets, int channels) {
    List<List<List<Integer>>> combinations = new ArrayList<>();
    System.out.println("packets: " + packets);

    if (channels == 1) {
      List<List<Integer>> combination1 = new ArrayList<>();
      for(int i = 1; i <= packets.size(); i++) {
        combination1.add(packets.subList(0, i));
      }
      combinations.add(combination1);
    } else if (channels == 2) {
      List<List<Integer>> combination2 = new ArrayList<>();
      for(int i = 1; i <= packets.size() - 1; i++) {
        combination2.add(packets.subList(0, i));
      }
      combinations.add(combination2);

      List<List<Integer>> combination3 = new ArrayList<>();
      for(int i = packets.size() - 1; i >= 0; i--) {
        combination3.add(packets.subList(i, packets.size()));
      }
      combinations.add(combination3);
    }

    System.out.println("combinations: " + combinations);
    return combinations;
  }
}
