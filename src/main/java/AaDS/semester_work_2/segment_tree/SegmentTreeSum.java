package AaDS.semester_work_2.segment_tree;

public class SegmentTreeSum implements SegmentTree_I {
    private int[] tree;
    private int[] data;
    private final int n;

    public SegmentTreeSum(int n) {
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
            tree[i] = tree[2 * i] + tree[2 * i + 1];
            operations++;
        }
        endTime = System.nanoTime();
        System.out.printf("Build for tree_sum: Time = %d ns, Operations = %d%n",
                endTime - startTime, operations);
    }

    // Поиск суммы на отрезке
    public void query(int l, int r) {
        // Сохраняю переменные для вывода отрезка
        int originalL = l;
        int originalR = r;

        long startTime = System.nanoTime();
        long operations = 0;

        int res = 0;
        l += n;
        r += n;

        while (l < r) {
            if (l % 2 == 1) {
                res += tree[l++];
                operations++;
            }
            if (r % 2 == 1) {
                res += tree[--r];
                operations++;
            }
            l /= 2;
            r /= 2;
        }

        long endTime = System.nanoTime();
        System.out.printf("Query sum: Range [%d, %d), Result = %d, Time = %d ns, Operations = %d%n",
                originalL, originalR, res, endTime - startTime, operations);
    }

    // Обновление структуры (добавление, изменение элементов)
    public void update(int i, int value) {
        long startTime = System.nanoTime();
        long operations = 0;

        int pos = i + n;
        tree[pos] = value;
        while (pos > 1) {
            pos /= 2;
            tree[pos] = tree[2 * pos] + tree[2 * pos + 1];
            operations++;
        }

        long endTime = System.nanoTime();
        System.out.printf("Update for tree_sum: Index = %d, New Value = %d, Time = %d ns, Operations = %d%n",
                i, value, endTime - startTime, operations);
    }

    // То же самое, что и апдейт, на всякий случай оставлю
    public void delete(int index) {
        // Обнуляем элемент (эмулируем удаление)
        update(index, 0); // используем '0' как нейтральный элемент для суммы
    }

    // Метод чтобы инициализировать наш массив, на котором мы построим нашу структуру
    public void setData(int[] newData) {
        this.data = newData;
    }
}


