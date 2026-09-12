import java.util.PriorityQueue;

public class PatientQueue {

    private final PriorityQueue<Object> queue;

    public PatientQueue() {
        queue = new PriorityQueue<>(
            (p1, p2) -> {
                int priority1 = ((PatientQueueItem) p1).getTriageLevel().getLevel();
                int priority2 = ((PatientQueueItem) p2).getTriageLevel().getLevel();

                return Integer.compare(priority1, priority2);
            }
        );
    }

    public void addPatient(Object patient) {
        queue.add(patient);
    }

    public Object getNextPatient() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getQueueSize() {
        return queue.size();
    }

    private interface PatientQueueItem {
        TriageLevel getTriageLevel();
    }

    private interface TriageLevel {
        int getLevel();
    }
}