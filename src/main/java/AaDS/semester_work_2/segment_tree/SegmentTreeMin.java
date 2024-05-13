package AaDS.semester_work_2.segment_tree;

public class SegmentTreeMin implements SegmentTree_I{
    private int[] tree;
    private int[] data;
    private final int n;

    public SegmentTreeMin(int n) {
        this.n = n;
        this.tree = new int[2 * n + 1];
        this.data = new int[n];
    }


    public void build() {
        long startTime, endTime, operations;
        startTime = System.nanoTime();
        operations = 0;

        // Строим листья
        for (int i = 0; i < n; i++) {
            tree[n + i] = data[i];
            operations++;
        }

        // Строим внутренние узлы
        for (int i = n - 1; i > 0; i--) {
            tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
            operations++;
        }
        endTime = System.nanoTime();
        System.out.printf("Build for tree_min: Time = %d ns, Operations = %d%n",
                endTime - startTime, operations);
    }

    // Поиск минимума на отрезке
    public void query(int l, int r) {
        // Сохраняю переменные для вывода отрезка
        int originalL = l;
        int originalR = r;

        long startTime = System.nanoTime();
        long operations = 0;

        int minResult = Integer.MAX_VALUE;
        l += n;
        r += n;

        while (l < r) {
            if (l % 2 == 1) {
                minResult = Math.min(minResult, tree[l++]);
                operations++;
            }
            if (r % 2 == 1) {
                minResult = Math.min(minResult, tree[--r]);
                operations++;
            }
            l /= 2;
            r /= 2;
        }

        long endTime = System.nanoTime();
        System.out.printf("Query Min: Range [%d, %d), Result = %d, Time = %d ns, Operations = %d%n",
                originalL, originalR, minResult, endTime - startTime, operations);
    }

    // Обновление структуры (добавление, изменение элементов)
    public void update(int i, int value) {
        long startTime = System.nanoTime();
        long operations = 0;

        int pos = i + n;
        tree[pos] = value;
        while (pos > 1) {
            pos /= 2;
            tree[pos] = Math.min(tree[2 * pos], tree[2 * pos + 1]); // Строчка если хотим находить минимум на отрезке
            operations++;
        }

        long endTime = System.nanoTime();
        System.out.printf("Update for tree_min: Index = %d, New Value = %d, Time = %d ns, Operations = %d%n",
                i, value, endTime - startTime, operations);
    }

    // То же самое, что и апдейт, на всякий случай оставлю
    public void delete(int index) {
        // Эмулируем удаление
        update(index, Integer.MAX_VALUE); // если ищем минимум на отрезке
    }

    // Метод чтобы инициализировать наш массив, на котором мы построим нашу структуру
    public void setData(int[] newData) {
        this.data = newData;
    }
}


