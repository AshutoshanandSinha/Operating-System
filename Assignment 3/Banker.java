
import java.util.*;

public class Banker {

     Scanner sc = new Scanner(System.in);
     static Client[] numOfClients = new Client[4];
     static String[] typesOfClients = new String[4];
     double[] Emax = new double[typesOfClients.length];
     static double[][] currentAllocated = new double[numOfClients.length][typesOfClients.length];
     static double[][] requiredResource = new double[numOfClients.length][typesOfClients.length];
     static double[] availableCurrently = new double[numOfClients.length];

    public void maxAmount(){

        System.out.println("================================");
        System.out.println("Initialize/Fetch Max amount :");
        System.out.println("================================");

        for(int index =0; index<Emax.length ; index++){
            System.out.println("Enter max amount for account type "+ typesOfClients[index]+ " :");
            Emax[index] = sc.nextDouble();
        }
    }

    public void allocateResource(){

        System.out.println("================================");
        System.out.println("Allocate Resources:");
        System.out.println("================================");

        for(int index = 0; index< numOfClients.length; index++){
            for(int j = 0 ; j < typesOfClients.length; j++){
                System.out.println("Enter amount to give to client " + numOfClients[index].name +" for account type: "+ typesOfClients[j]);
                currentAllocated[index][j] = sc.nextDouble();
            }
        }
    }
    public void requiredResource(){

        System.out.println("================================");
        System.out.println("Required Resources:");
        System.out.println("================================");

        for(int index = 0; index< numOfClients.length; index++){
            for(int j = 0 ; j < typesOfClients.length; j++){
                System.out.println("Enter amount to give to client " + numOfClients[index].name +" for account type: "+ typesOfClients[j]);
                requiredResource[index][j] = sc.nextDouble();
            }
        }
    }

    public void calculateAvailable(){


        for(int index = 0; index< typesOfClients.length; index++){
            int sum=0;
            for(int j =0; j<numOfClients.length; j++)
                sum += currentAllocated[j][index];

            availableCurrently[index] = Emax[index] - sum;
        }
    }

    public void printAll(){
        System.out.println("================================");
        System.out.println("Maximum:");
        System.out.println("================================");

        for(double i : Emax)
            System.out.print(i+" ");

        System.out.println();

        System.out.println("================================");
        System.out.println("Available:");
        System.out.println("================================");

        for(double i : availableCurrently)
            System.out.print(i+" ");

        System.out.println();

        System.out.println("================================");
        System.out.println("Currently Allocated");
        System.out.println("================================");

        for(int index =0 ; index < numOfClients.length;index++){
            System.out.println("For client : " + numOfClients[index].name);
            for(int j = 0; j < typesOfClients.length; j++) {
                System.out.print(currentAllocated[index][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("================================");
        System.out.println("Required Resource");
        System.out.println("================================");
        for(int index =0 ; index < numOfClients.length;index++){
            System.out.println("For client : " + numOfClients[index].name);
            for(int j = 0; j < typesOfClients.length; j++) {
                System.out.print(requiredResource[index][j] + " ");
            }
            System.out.println();
        }

    }

    public void initializeTypeClients(){
        typesOfClients[0]= "checkingsAccount" ;
        typesOfClients[1]= "savingsAccount" ;
        typesOfClients[2]= "fixedDepositAccount" ;
        typesOfClients[3]= "RecurringDeposit" ;
    }

    public void initializeClients(){


        for(int  index =0; index< numOfClients.length ; index++){
            System.out.println("Enter Name for "+ (index+1) + " client: ");
            Client c = new Client();
            c.name =sc.nextLine();
            numOfClients[index] = c;
        }
    }

    public void isSafe(){

        System.out.println();

        System.out.println("CHECKING FOR SAFE SEQUENCE...");

        Set<Client> set = new HashSet<>();

        Queue<Client> queue = new LinkedList<>();

        for(Client i : numOfClients)
           set.add(i);

        int counter = 0;

        while(!set.isEmpty()) {

            for (int index = 0; index < numOfClients.length; index++) {
                counter = 0;
                boolean isless = true;
                System.out.println("Accessing Client :"+ numOfClients[index].getName());
                for (int j = 0; j < typesOfClients.length; j++) {
                    if (requiredResource[index][j] > availableCurrently[index]) {
                        isless = false;
                        System.out.println("Resource can not be allocated to " + numOfClients[index].getName()+ " currently.");
                    }
                }
                if (isless && set.contains(numOfClients[index])) {
                    System.out.println("Resourse can be allocated to "+ numOfClients[index].getName());
                    queue.add(numOfClients[index]);

                    set.remove(numOfClients[index]);
                    //Give available resource to respective process and then update available
                    System.out.println("Updating Available...");
                        for (int j = 0; j<typesOfClients.length; j++)
                            availableCurrently[j] += currentAllocated[index][j];
                 counter = 1;
                }
            }

            if(counter == 0 && !set.isEmpty())
            {
                System.out.println("No Safe sequence!");
                return;
            }
        }

        System.out.println("Safe Sequence is as follows :");

        while (!queue.isEmpty())
            System.out.print(queue.poll().getName()+"-->");

    }

    public static void main(String[] args){

        Banker b = new Banker();

        b.initializeClients();
        b.initializeTypeClients();
        b.maxAmount();
        b.allocateResource();
        b.requiredResource();
        b.calculateAvailable();
        b.printAll();
        b.isSafe();
    }

}
