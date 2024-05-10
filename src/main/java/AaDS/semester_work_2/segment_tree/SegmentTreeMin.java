package AaDS.semester_work_2.segment_tree;

public class SegmentTreeMin implements SegmentTree_I{
    private int[] tree;
    private int[] data;
    private final int n;

    public SegmentTreeMin(int n) {
        this.n = n;
        this.tree = new int[4 * n];
        this.data = new int[n];
    }

    public void build() {
        long startTime, endTime, operations;
        for (int i = 0; i < n; i++) {
            startTime = System.nanoTime();
            operations = 0;

            int pos = i + n;
            tree[pos] = data[i];
            while (pos > 1) {
                pos /= 2;
                tree[pos] = Math.min(tree[2 * pos], tree[2 * pos + 1]);
                operations++;
            }

            endTime = System.nanoTime();
            System.out.printf("Add for tree_min: Index = %d, Time = %d ns, Operations = %d%n",
                    i, endTime - startTime, operations);
        }
    }

    // Поиск минимума на отрезке
    public void query(int l, int r) {
        int originalLeft = l, originalRight = r;

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
                originalLeft, originalRight, minResult, endTime - startTime, operations);
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


