package AaDS.semester_work_1.test_generation;

import java.awt.*;
import java.io.*;
import java.util.*;

public class DataGenerator {

    public static void generateData() {
        Random rand = new Random();
        for (int i = 0; i < 50; i++) { // Генерация 50 файлов
            int n = 100 + rand.nextInt(1000); // Генерация количества точек от 100 до 1000 (сложность алгоритма не позволяет брать слишком много точек, тк это будет очень долго)
            ArrayList<Point> points = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                points.add(new Point(rand.nextInt(-100,100), rand.nextInt(-100,100))); // Генерация точек с координатами от -100 до 100
            }
            try {
                String fileName = "data" + i + ".txt";
                PrintWriter writer = new PrintWriter(new File(fileName));
                for (Point point : points) {
                    writer.println(point.x + " " + point.y);
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
