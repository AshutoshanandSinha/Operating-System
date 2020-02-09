import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PagingNFUAging {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int counter=0;
        msb=128; //msb - most significant bit
        System.out.println("Enter maximum frame size:");
        int framesize = sc.nextInt();
        sc.close();
        File file = new File("/Users/ashu/Desktop/sample.txt");
        file.createNewFile();
        Scanner scan = new Scanner(file);
        int [] pageRef = new int[1000];// page references
        int [][] frameFaults = new int[framesize][2];// pagenumber and no of faults
        int [][] memory = new int[8][2];// to store the current page and its counter value 

        @SuppressWarnings("Resource")
        PrintWriter writer = new PrintWriter("/Users/ashu/Desktop/sample.txt");
//generating sequence randomly
        for(int i=0;i<1000;i++){
            int var=(int)(Math.floor(Math.random()*framesize));
            writer.println(var+1);
        }
        writer.close();

//initializing array from randomly generated sequence file
        while(scan.hasNextLine()){
            String s = scan.nextLine().trim();
            pageRef[counter] = Integer.parseInt(s);
            counter++;
        }

        scan.close();
// Checking for each page reference 
        for(int page :pageRef){
            boolean notFound = false;
            boolean breakloop =false;
            while(!notFound){
                for(int []m:memory){
                    if(m[0]==page){
                        m[1]=m[1]|msb;
                        breakloop=true;
                        break;
                    }
                }
                notFound=true;
            }
            if(notFound == true && !breakloop){

                for(int []m:memory){
                    m[1]= m[1]>>1;
                }
                int low = 0;
                for(int i11 = 0;i11<7;i11++){
                    if(memory[i11][1] > memory[i11+1][1]){
                        low = i11+1;
                    }
                }
                memory[low][0]=i;
                memory[low][1]=128;
                frameFaults[i-1][0]=i;
                frameFaults[i-1][1]=frameFaults[i-1][1]+1;
            }


        }

        System.out.println("========================");
        System.out.println("Memory:");
        System.out.println("========================");

        for(int []mem:memory){
            System.out.println(mem[0]+"    "+mem[1]);
        }
        System.out.println("========================");
        System.out.println("Page Faults:");
        System.out.println("========================");

        for(int []pf:frameFaults){
            if(pf[0] >0){
                System.out.println(pf[0]+"    "+pf[1]);
            }
        }
    }

}
