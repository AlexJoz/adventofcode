import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by A. Joz on 1/8/2016.
 * <p>
 * --- Day 23: Opening the Turing Lock ---
 * <p>
 * Little Jane Marie just got her very first computer for Christmas from some unknown benefactor.
 * It comes with instructions and an example program, but the computer itself seems to be malfunctioning.
 * She's curious what the program does, and would like you to help her run it.
 * <p>
 * The manual explains that the computer supports two registers and six instructions
 * (truly, it goes on to remind the reader, a state-of-the-art technology).
 * The registers are named a and b, can hold any non-negative integer, and begin with a value of 0. The instructions are as follows:
 * <p>
 * hlf r sets register r to half its current value, then continues with the next instruction.
 * tpl r sets register r to triple its current value, then continues with the next instruction.
 * inc r increments register r, adding 1 to it, then continues with the next instruction.
 * jmp offset is a jump; it continues with the instruction offset away relative to itself.
 * jie r, offset is like jmp, but only jumps if register r is even ("jump if even").
 * jio r, offset is like jmp, but only jumps if register r is 1 ("jump if one", not odd).
 * All three jump instructions work with an offset relative to that instruction.
 * The offset is always written with a prefix + or - to indicate the direction of the jump (forward or backward, respectively).
 * For example, jmp +1 would simply continue with the next instruction, while jmp +0 would continuously jump back to itself forever.
 * <p>
 * The program exits when it tries to run an instruction beyond the ones defined.
 * <p>
 * For example, this program sets a to 2, because the jio instruction causes it to skip the tpl instruction:
 * <p>
 * inc a
 * jio a, +2
 * tpl a
 * inc a
 * What is the value in register b when the program in your puzzle input is finished executing?
 * <p>
 * --- Part Two ---
 * <p>
 * The unknown benefactor is very thankful for releasi-- er, helping little Jane Marie with her computer.
 * Definitely not to distract you, what is the value in register b after the program is finished executing if register a starts as 1 instead?
 */
public class Day23 {
    public static void main(String[] args) {
        Map<Character, Integer> registers = new HashMap<Character, Integer>();
        ArrayList<String> list = new ArrayList<>();
        registers.put('a', 1);
        registers.put('b', 0);

        int index = 0;

        // fill list with each command line as one element;
        Collections.addAll(list, args);

        while (index < list.size()) {
            String[] temp = list.get(index).split(" ");
            switch (temp[0]) {
                case "hlf":
                    registers.put(temp[1].charAt(0), registers.get(temp[1].charAt(0)) / 2);
                    break;
                case "tpl":
                    registers.put(temp[1].charAt(0), registers.get(temp[1].charAt(0)) * 3);
                    break;
                case "inc":
                    registers.put(temp[1].charAt(0), registers.get(temp[1].charAt(0)) + 1);
                    break;
                case "jmp":
                    int num = Integer.parseInt(temp[1]);
                    index += num - 1;
                    break;
                case "jie":
                    if (registers.get(temp[1].charAt(0)) % 2 == 0) {
                        index += Integer.parseInt(temp[2]) - 1;
                    }
                    break;
                case "jio":
                    if (registers.get(temp[1].charAt(0)) == 1) {
                        index += Integer.parseInt(temp[2]) - 1;
                    }
                    break;
            }
            index++;
        }
        // part 1:
        System.out.println(registers.get('b'));
        // part 2:
        // change registers.put('a', 0); -> registers.put('a', 1); in the beginning
    }
}

