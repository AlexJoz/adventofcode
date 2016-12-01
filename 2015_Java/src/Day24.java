import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by A. Joz on 1/8/2016.
 * <p>
 * --- Day 24: It Hangs in the Balance ---
 * <p>
 * It's Christmas Eve, and Santa is loading up the sleigh for this year's deliveries.
 * However, there's one small problem: he can't get the sleigh to balance.
 * If it isn't balanced, he can't defy physics, and nobody gets presents this year.
 * <p>
 * No pressure.
 * <p>
 * Santa has provided you a list of the weights of every package he needs to fit on the sleigh.
 * The packages need to be split into three groups of exactly the same weight, and every package has to fit.
 * The first group goes in the passenger compartment of the sleigh, and the second and third go in containers on either side.
 * Only when all three groups weigh exactly the same amount will the sleigh be able to fly. Defying physics has rules, you know!
 * <p>
 * Of course, that's not the only problem.
 * The first group - the one going in the passenger compartment - needs as few packages as possible so that Santa has some legroom left over.
 * It doesn't matter how many packages are in either of the other two groups, so long as all of the groups weigh the same.
 * <p>
 * Furthermore, Santa tells you, if there are multiple ways to arrange the packages such
 * that the fewest possible are in the first group, you need to choose the way
 * where the first group has the smallest quantum entanglement to reduce the chance of any "complications".
 * The quantum entanglement of a group of packages is the product of their weights, that is,
 * the value you get when you multiply their weights together. Only consider quantum entanglement
 * if the first group has the fewest possible number of packages in it and all groups weigh the same amount.
 * <p>
 * For example, suppose you have ten packages with weights 1 through 5 and 7 through 11.
 * For this situation, some of the unique first groups, their quantum entanglements, and a way to divide the remaining packages are as follows:
 * <p>
 * Group 1;             Group 2; Group 3
 * 11 9       (QE= 99); 10 8 2;  7 5 4 3 1
 * 10 9 1     (QE= 90); 11 7 2;  8 5 4 3
 * 10 8 2     (QE=160); 11 9;    7 5 4 3 1
 * 10 7 3     (QE=210); 11 9;    8 5 4 2 1
 * 10 5 4 1   (QE=200); 11 9;    8 7 3 2
 * 10 5 3 2   (QE=300); 11 9;    8 7 4 1
 * 10 4 3 2 1 (QE=240); 11 9;    8 7 5
 * 9 8 3      (QE=216); 11 7 2;  10 5 4 1
 * 9 7 4      (QE=252); 11 8 1;  10 5 3 2
 * 9 5 4 2    (QE=360); 11 8 1;  10 7 3
 * 8 7 5      (QE=280); 11 9;    10 4 3 2 1
 * 8 5 4 3    (QE=480); 11 9;    10 7 2 1
 * 7 5 4 3 1  (QE=420); 11 9;    10 8 2
 * Of these, although 10 9 1 has the smallest quantum entanglement (90),
 * the configuration with only two packages, 11 9, in the passenger compartment gives Santa the most legroom and wins.
 * In this situation, the quantum entanglement for the ideal configuration is therefore 99.
 * Had there been two configurations with only two packages in the first group,
 * the one with the smaller quantum entanglement would be chosen.
 * <p>
 * What is the quantum entanglement of the first group of packages in the ideal configuration?
 * <p>
 * --- Part Two ---
 * <p>
 * That's weird... the sleigh still isn't balancing.
 * <p>
 * "Ho ho ho", Santa muses to himself. "I forgot the trunk".
 * <p>
 * Balance the sleigh again, but this time, separate the packages into four groups instead of three.
 * The other constraints still apply.
 * <p>
 * Given the example packages above, this would be some of the new unique first groups,
 * their quantum entanglements, and one way to divide the remaining packages:
 * <p>
 * <p>
 * 11 4    (QE=44); 10 5;   9 3 2 1; 8 7
 * 10 5    (QE=50); 11 4;   9 3 2 1; 8 7
 * 9 5 1   (QE=45); 11 4;   10 3 2;  8 7
 * 9 4 2   (QE=72); 11 3 1; 10 5;    8 7
 * 9 3 2 1 (QE=54); 11 4;   10 5;    8 7
 * 8 7     (QE=56); 11 4;   10 5;    9 3 2 1
 * Of these, there are three arrangements that put the minimum (two) number of packages in the first group:
 * 11 4, 10 5, and 8 7. Of these, 11 4 has the lowest quantum entanglement, and so it is selected.
 * <p>
 * Now, what is the quantum entanglement of the first group of packages in the ideal configuration?
 */
public class Day24 {
    static Set<Long> group1 = new HashSet<>();
    static Set<Long> group2 = new HashSet<>();
    static Set<Long> group3 = new HashSet<>();
    static Set<Long> group4 = new HashSet<>();
    static LinkedList<Long> collect = new LinkedList<>();

    static long minQuantum = Long.MAX_VALUE;
    static long minL = Long.MAX_VALUE;

    public static void main(String[] args) {
        fillCollect(args);

        // sum for part 1
        final long sum3 = collect.stream().reduce((a, b) -> a + b).get() / 3;
        // sum for part 2
        final long sum4 = collect.stream().reduce((a, b) -> a + b).get() / 4;

/**************************************part1**********************************
 U can use code like this for the first part
 Don't forget to sort collect via Comparator.reverseOrder();

 while (getCurrentSum(group1) != sum3 && !collect.isEmpty()) {
 if ((getCurrentSum(group1) + collect.peekFirst()) > sum3) {
 if ((getCurrentSum(group2) + collect.peekFirst()) > sum3) group3.add(collect.removeFirst());
 else group2.add(collect.removeFirst());
 } else {
 group1.add(collect.removeFirst());
 }
 } System.out.println(getQuantum(group1));
 ****************************************************************************/

        // randomly fills 4 groups and when lengths of all of them are equal - computes min quantum and group.len..
        // when nothing changes in the output -> here's result ^^
        while (true) {
            fillCollect(args);
            fillGroups(sum4);
            if (getCurrentSum(group1) == getCurrentSum(group2) && getCurrentSum(group3) == getCurrentSum(group4) && getCurrentSum(group1) == getCurrentSum(group4)) {
                if (minL > getMinLen()) minL = getMinLen();
                if (minQuantum > getMinQ()) minQuantum = getMinQ();

                System.out.println("Quantum= " + minQuantum + " Group.len= " + minL);
            }
        }
    }

    // generates random list of weights from input args
    private static void fillCollect(String... args) {
        collect.clear();
        collect = Stream.of(args).map(Long::parseLong).collect(Collectors.toCollection(LinkedList<Long>::new));
        Collections.shuffle(collect);
    }

    // returns min len from 4 groups
    private static long getMinLen() {
        long out = Long.MAX_VALUE;
        return Math.min(
                out,
                Math.min(
                        Math.min(
                                Math.min(
                                        group1.size(),
                                        group2.size()
                                ),
                                group3.size()
                        ),
                        group4.size()
                )
        );
    }

    //returns min quantum from 4 groups
    private static long getMinQ() {
        long out = Long.MAX_VALUE;
        return Math.min(
                out,
                Math.min(
                        Math.min(
                                Math.min(
                                        getQuantum(group1),
                                        getQuantum(group2)
                                ),
                                getQuantum(group3)
                        ),
                        getQuantum(group4)
                )
        );
    }

    private static void fillGroups(long sum) {
        group1.clear();
        group2.clear();
        group3.clear();
        group4.clear();
        while (getCurrentSum(group1) != sum && !collect.isEmpty()) {
            if ((getCurrentSum(group1) + collect.peekFirst()) > sum)
                if ((getCurrentSum(group2) + collect.peekFirst()) > sum)
                    if ((getCurrentSum(group3) + collect.peekFirst()) > sum) group4.add(collect.removeFirst());
                    else group3.add(collect.removeFirst());
                else group2.add(collect.removeFirst());
            else group1.add(collect.removeFirst());
        }
    }

    // returns sum of weights in the group
    private static long getCurrentSum(Set<Long> group) {
        return group.stream().mapToLong(Long::valueOf).sum();
    }

    // returns quantum entanglement of the group
    private static long getQuantum(Set<Long> group) {
        return group.stream().reduce((a, b) -> a * b).get();
    }
}