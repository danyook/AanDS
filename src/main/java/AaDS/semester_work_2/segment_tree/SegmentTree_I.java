package AaDS.semester_work_2.segment_tree;

public interface SegmentTree_I {

    void build();
    void update(int i, int value);
    void query(int l, int r);


}
