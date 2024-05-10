package AaDS.semester_work_2.segment_tree;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int size = 10000;
        int[] data = GenerateTest.createRandomArray(size);

        Random random = new Random();

        SegmentTreeSum stSum = new SegmentTreeSum(size);
        stSum.setData(data);
        stSum.build();

        SegmentTreeMin stMin = new SegmentTreeMin(size);
        stMin.setData(data);
        stMin.build();

        SegmentTreeMax stMax = new SegmentTreeMax(size);
        stMax.setData(data);
        stMax.build();

        // Запросы
        for (int i = 0; i < 100; i++) {
            int l = random.nextInt(size);
            int r = random.nextInt(l + 1, size + 1);
            stSum.query(l, r);
            stMin.query(l, r);
            stMax.query(l, r);
        }

        // Обновления
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(size);
            int value = random.nextInt(1000);
            stSum.update(index, value);
            stMin.update(index, value);
            stMax.update(index, value);
        }
    }
}