import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class testQueue {
    public static class arrivalTimeComparate implements Comparator<ProcessStructure> {

        @Override
        public int compare(ProcessStructure arrivalTime1, ProcessStructure arrivalTime2) {
            if(arrivalTime1.getArrivalTime() < arrivalTime2.getArrivalTime()){
                return 1;
            }
            else if(arrivalTime1.getArrivalTime() > arrivalTime2.getArrivalTime()){
                return -1;
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        ProcessStructure p = new ProcessStructure();
        p.setDuration(1);
        p.setArrivalTime(2);
        p.setPriority(3);
        p.setProcessID(4);
        PriorityQueue<ProcessStructure> test = new PriorityQueue<>(10,new arrivalTimeComparate());
        test.add(p);
    }
}
