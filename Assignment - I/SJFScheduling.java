import java.util.*;
/** The Shortest Job First program.
 * Takes processes (processid, burst time, and arrival time for each process) and schedules pertaining the rules of Shortest Job First.
 * @author Ashutoshanand Sinha
 */
public class SJFScheduling {

    static LinkedList<Proc> jobQueue = new LinkedList<>();


    Proc findNextProcess(int prevProcessFinishTime) {
        if (!jobQueue.isEmpty()) {
            Proc p = jobQueue.get(0);
            int burstTime = p.getTimeBurst();
            int arrivalTime = p.getArrivalTime();

            for (int i = 1; i < jobQueue.size(); i++) {
                if (jobQueue.get(i).getArrivalTime() > prevProcessFinishTime)
                    break;
                if (jobQueue.get(i).getTimeBurst() < burstTime) {
                    p = jobQueue.get(i);
                    burstTime = jobQueue.get(i).getTimeBurst();
                    arrivalTime = jobQueue.get(i).arrivalTime;
                } else if (jobQueue.get(i).getTimeBurst() == burstTime  && jobQueue.get(i).getArrivalTime() < arrivalTime){
                    p = jobQueue.get(i);
                    burstTime = jobQueue.get(i).getTimeBurst();
                    arrivalTime = jobQueue.get(i).arrivalTime;
                }

            }
            jobQueue.remove(p);
            return p;
        }

        return null;
    }

    //Started Scheduling
    void startScheduling() {
        Proc p = jobQueue.poll();
        System.out.println(p.getProcessid() + " started running at " + p.getArrivalTime());
        int curProcFinishTime = p.getArrivalTime() + p.getTimeBurst();
        p.setCompletedTime(curProcFinishTime);
        int prevProcFinishTime = curProcFinishTime;
        System.out.println(p.getProcessid() + " finished at " + p.getCompletedTime());

        while ((p = findNextProcess(curProcFinishTime)) != null) {
            if (p.getArrivalTime() > prevProcFinishTime) {
                System.out.println(p.getProcessid() + " started running at " + p.arrivalTime);
                curProcFinishTime = p.getArrivalTime() + p.getTimeBurst();
                p.setCompletedTime(curProcFinishTime);
                System.out.println(p.getProcessid() + " finished at " + p.getCompletedTime());
            } else {
                System.out.println(p.getProcessid() + " started running at " + prevProcFinishTime);
                curProcFinishTime = prevProcFinishTime + p.getTimeBurst();
                p.setCompletedTime(curProcFinishTime);
                System.out.println(p.getProcessid() + " finished at " + p.getCompletedTime());
            }
            prevProcFinishTime = curProcFinishTime;
        }
    }

    //Adding processes to process queue along with maintaining thread safety
    public synchronized void addProcess(Proc p) {
        jobQueue.add(p);
        Collections.sort(jobQueue, new SjfCompare());

    }


    void calculateAvgWaiting(List<Proc> list){

        if(list.size()==0)
            return;

        int time=0;
        for(Proc index : list )
            time = time + (index.completedTime  - index.timeBurst - index.arrivalTime);

        System.out.println("===============================");
        System.out.println("Average Waiting Time : " + time/list.size());
        System.out.println("===============================");

    }


    void calculateAvgTurnAroundTime(List<Proc> list){

        if(list.size()==0)
            return;

        int time=0;
        for(Proc index : list )
            time = time + (index.completedTime  - index.arrivalTime);

        System.out.println("===============================");
        System.out.println("Average Turn Around Time : " + time/list.size());
        System.out.println("===============================");

    }

    void calculateCompletion(List<Proc> list){

        if(list.size()==0)
            return;

        System.out.println("===============================");
        System.out.println("Completion Time : ");
        System.out.println("===============================");
        for(Proc index : list )
            System.out.println(index.getProcessid() +  "  : " + index.completedTime);

    }

    public static void main(String[] args) {

        //Processes
        Proc p1 = new Proc("P1", 7, 0);
        Proc p2 = new Proc("P2", 4, 2);
        Proc p3 = new Proc("P3", 1, 4);
        Proc p4 = new Proc("P4", 4, 5);
        Proc p5 = new Proc("P5", 4, 5);


        //Scheduling Object
        SJFScheduling sjf = new SJFScheduling();

        //List for Processes
        List<Proc> processList = new ArrayList<>();

        //initializing processes
        sjf.addProcess(p1);
        sjf.addProcess(p2);
        sjf.addProcess(p3);
        sjf.addProcess(p4);
        sjf.addProcess(p5);


        processList.add(p1);
        processList.add(p2);
        processList.add(p3);
        processList.add(p4);
        processList.add(p5);

        //Started Scheduling
        sjf.startScheduling();

        //Calculating Average waiting time
        sjf.calculateAvgWaiting(processList);

        //Calculating Average Turn Around time
        sjf.calculateAvgTurnAroundTime(processList);

        //Calculating Average Completion time
        sjf.calculateCompletion(processList);



    }
}
