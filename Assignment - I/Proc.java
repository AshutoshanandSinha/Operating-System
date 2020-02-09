import java.util.Comparator;
/** Processes Information.
 * Defining  processes Information (processid, burst time, and arrival time for each process)  and Custom Comparator.
 * @author Ashutoshanand Sinha
 */
class Proc {

    String processid;
    int timeBurst;
    int arrivalTime;
    int completedTime;

    public  Proc(String processid, int timeBurst, int arrivalTime){
        setProcessid(processid);
        setTimeBurst(timeBurst);
        setArrivalTime(arrivalTime);
    }


    public String getProcessid() {
        return processid;
    }

    public void setProcessid(String processid) {
        this.processid = processid;
    }

    public int getTimeBurst() {
        return timeBurst;
    }

    public void setTimeBurst(int timeBurst) {
        this.timeBurst = timeBurst;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(int completedTime) {
        this.completedTime = completedTime;
    }

}


//Processes compared and stored on basis of arrival and burst time, using customised comparision
class SjfCompare implements Comparator<Proc> {


    public int compare(Proc p1, Proc p2) {

        if (p1.arrivalTime < p2.arrivalTime)
            return -1;
        else if (p1.arrivalTime > p2.arrivalTime)
            return 1;
        else{

            if(p1.timeBurst > p2.timeBurst)
                return 1;
            else
                return -1;
        }

    }
}
