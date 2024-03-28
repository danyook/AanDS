package AaDS.semester_work_1.Jarvis_algorithm;

import AaDS.semester_work_1.test_generation.DataGenerator;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DataGenerator.generateData();


        // Считывание данных из 50 сгенерированных файлов и добавляние точек из файла в список points
        for (int i = 0; i < 50; i++) {

            ArrayList<Point> points = new ArrayList<>();
            String fileName = "data" + i + ".txt";

            File file = new File(fileName);
            try {
                Scanner scanner = new Scanner(new File(fileName));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    points.add(convertStringForPoint(line));
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            long before = System.currentTimeMillis();
            ArrayList<Point> convexHull = JarvisAlg.convexHull(points);
            long after = System.currentTimeMillis();

            printPoints(points);
            System.out.println("Для " + points.size() + " точек всего и для " + convexHull.size() +
                    " точек которые принадлежат выпуклой оболочке, количество итераций = " + JarvisAlg.countIter);
            file.delete(); // Удаление сгенерированного файла после того как считали из него данные

            printHull(convexHull);
            System.out.println("Время работы алгоритма: " + (after - before));
            System.out.println("-----");
            System.out.println();

        }

    }

    // Конвертация строки из файла в точку
    public static Point convertStringForPoint(String point) {
        String[] strPoint = point.split(" ");
        int x = Integer.parseInt(strPoint[0]);
        int y = Integer.parseInt(strPoint[1]);
        return new Point(x, y);
    }

    // Вывод оболочки
    public static void printHull(ArrayList<Point> convexHull) {
        System.out.println("Answer for next file:");

        for (Point p : convexHull) {
            System.out.print("(" + p.x + ", " + p.y + ")" + "  ");
        }
        System.out.println();
    }

    public static void printPoints(ArrayList<Point> points) {
        for (Point p : points) {
            System.out.print("(" + p.x + ", " + p.y + ")" + "  ");
        }
        System.out.println();



    }
}
