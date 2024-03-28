package AaDS.semester_work_1.Jarvis_algorithm;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class JarvisAlg {
    public static int countIter;

    // q - наша текущая точка, которая уже в оболочке,
    // p - точки, где мы ищем точку с мин углом в полярных координатах
    public static double findAngle(Point q, Point p) {
        Point demoP = new Point(p.x - q.x, p.y - q.y);

        if (demoP.y == 0 && demoP.x > 0) {
            return Math.toRadians(0);
        }
        if (demoP.y == 0 && demoP.x < 0) {
            return Math.toRadians(180);
        }
        if (demoP.y > 0 && demoP.x == 0) {
            return Math.toRadians(90);
        }
        if (demoP.y < 0 && demoP.x == 0) {
            return Math.toRadians(270);
        }

        double ans = (Math.atan2(demoP.y, demoP.x));

        while (ans < 0) {
            ans += 2 * Math.PI;
        }
        return ans;
    }

    // Нахождения угла между двумя прямыми заданными 3 точками
    public static double findAngle(Point lastCurr, Point curr, Point mbNextCurr) {
        if (lastCurr == curr) { // Случай когда у нас только стартовая точка и мы хотим найти следующую точку с мин углом
                                // Находим угол от прямой проходящей через точку curr и параллельной оси OX
            Point demoP = new Point(mbNextCurr.x - lastCurr.x, mbNextCurr.y - lastCurr.y);
            return Math.atan2(demoP.y, demoP.x);
        }
        // Вектор lastCurr,curr
        Point ab = new Point(curr.x - lastCurr.x, curr.y - lastCurr.y);
        // Вектор curr,mbNextCurr
        Point bc = new Point(mbNextCurr.x - curr.x, mbNextCurr.y - curr.y);

        // Скалярное произведение векторов lastCurr,curr и curr,mbNextCurr
        double dotProduct = ab.x * bc.x + ab.y * bc.y;

        // Нормали векторов lastCurr,curr и curr,mbNextCurr
        double normAB = Math.sqrt(Math.pow(ab.x, 2) + Math.pow(ab.y, 2));
        double normBC = Math.sqrt(Math.pow(bc.x, 2) + Math.pow(bc.y, 2));

        // Косинус угла между векторами
        double cosTheta = dotProduct / (normAB * normBC);

        // Угол между прямыми в радианах
        double angleRad = Math.acos(cosTheta);

        return angleRad;
    }

    // Создание оболочки для списка точек
    public static ArrayList<Point> convexHull(ArrayList<Point> points) {
        countIter = 0;

        if (points.size() < 3) {
            throw new IllegalArgumentException();
        }

        // Нахождение начальной точки с минимальной координатой Y
        Point start = points.get(0);
        for (Point p : points) {
            countIter++;
            if (p.y < start.y || (p.y == start.y && p.x > start.x)) {
                start = p;
            }
        }

        ArrayList<Point> hull = new ArrayList<>();
        hull.add(start);

        Point current = start;
        Point lastCurr = start;

        while (true) {
            Point mbNextTarget = points.get(0);
            double minAngl = Math.PI * 2;
            for (int i = 0; i < points.size(); i++) {
                countIter++;

                Point pointI = points.get(i);
                if (current == pointI) continue; // Если встретили вершину на которой мы и так сейчас находимся, то пропускаем

                if (findAngle(lastCurr, current, pointI) < minAngl || (findAngle(lastCurr , current, pointI) == minAngl
                        && distance(current, pointI) < distance(current, mbNextTarget))) { // Ищем минимальный угол или равный угол,
                                                                                            // но с меньшей дистанцией до current точки

                    if (!hull.contains(pointI) || pointI == start) { // Меняем значение минимального угла до точки и самой точки,
                        // только если ее нет в списке оболочки или она не является стартовой, чтобы не было бесконечного цикла

                        minAngl = findAngle(lastCurr, current, pointI);
                        mbNextTarget = pointI;

                    }
                }
            }
            if (mbNextTarget == start) { // Если наша следующая подходящая точка с мин углом равна стартовой,
                                        // то мы нашли все точки оболочки, заканчиваем
                break;
            }
            lastCurr = current;
            current = mbNextTarget;
            hull.add(current);
        }
        return hull;
    }

    // Вычисление расстояния между current точкой и двумя точками на случай, если минимальные углы до 2-х разных точек равны
    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}
