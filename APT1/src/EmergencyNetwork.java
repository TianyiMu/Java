public class EmergencyNetwork {
    int count;
    int[] supervisors;
    int[] times;
 
    public int compute(int index) {
        int ncalls = 0;
        for (int i = index + 1; i < count; i++)
            if (supervisors[i] == index) ncalls++;
        int[] calls = new int[ncalls];
        for (int i = index + 1, next = 0; i < count; i++)
            if (supervisors[i] == index) calls[next++] = times[i];
        java.util.Arrays.sort(calls);
        int time = 0;
        for (int i = 0; i < ncalls; i++)
            time = Math.max(time, i + 1 + calls[ncalls - 1 - i]);
        return time;
    }
 
    public int lagTime(int[] supervisors) {
        this.count = supervisors.length;
        this.supervisors = supervisors;
        this.times = new int[count];
        for (int i = count - 1; i >= 0; i--) times[i] = compute(i);
        return times[0];
    }
}