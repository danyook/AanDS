package AaDS.semester_work_2.segment_tree;

import java.util.Random;

public class GenerateTest {
    public static int[] createRandomArray(int size) {
        Random random = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(10000);
        }
        return result;
    }
}
