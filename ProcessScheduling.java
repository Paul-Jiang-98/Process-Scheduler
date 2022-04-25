import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ProcessScheduling {




    public static void stimulate(ArrayList<ProcessStructure> input, PriorityQueue<ProcessStructure> output, PrintWriter outputFile) {
        for (int i = 0; i < input.size(); i++) {
            outputFile.println("ID = " + input.get(i).getProcessID() + ", priority = " + input.get(i).getPriority() + ", duration" + input.get(i).getDuration() + ", arrival time" + input.get(i).getArrivalTime());
        }
        outputFile.println("Maximum wait time = " + 30);


        double totalWaitTime = 0;
        int currentTime = 0;
        boolean running = false;
        int allProcess = 0;
        double meanOfWait = 0;
        int waitTime = 0;
        int finishTime = 0;
        ProcessStructure currentlyRunning = null;


        while (!input.isEmpty()) {
            int smallest = input.get(0).getArrivalTime();
            int smallestIndex = 0;
            ProcessStructure p = new ProcessStructure();

            //Using for loop to find sort the arrival time in each element.

            for (int i = 0; i < input.size(); i++) {
                int temp = input.get(i).getArrivalTime();
                if (temp < smallest) {
                    smallest = temp;
                    smallestIndex = i;
                }
            }

            p = input.get(smallestIndex);


            if (p.getArrivalTime() <= currentTime) {
                if (input.isEmpty()) {
                    outputFile.println("\nD becomes empty at time " + currentTime + "\n");
                }
                //remove element that have the lowest priority.
                output.add(p);
                input.remove(p);
            }
            if (!output.isEmpty() && !running) {
                running = true;
                currentlyRunning = output.poll();


/**
                waitTime = currentTime - currentlyRunning.getArrivalTime();
                totalWaitTime += waitTime;

                */
                waitTime = currentTime - currentlyRunning.getArrivalTime();
                finishTime = currentTime + currentlyRunning.getDuration();

                totalWaitTime += waitTime;
                currentlyRunning.setWaitTime(waitTime);
                for(int i = 0; i < output.size();i++){
                    if(currentlyRunning.getWaitTime() >= 30 && currentlyRunning.getPriority() !=1){
                        int currentPriority = currentlyRunning.getPriority();
                        outputFile.println("PID = " + currentlyRunning.getProcessID() + " , wait time = " + currentlyRunning.getWaitTime() + " , current priority = "+ currentlyRunning.getPriority());
                        currentlyRunning.setPriority(currentPriority - 1);
                        outputFile.println("PID = " + currentlyRunning.getProcessID() + " , wait time = " + currentlyRunning.getWaitTime());

                    }
                }

                outputFile.println("Process removed from queue is: id = " + currentlyRunning.getProcessID() + " at time " + currentTime + ","
                        + " wait time = " + waitTime + "Total wait time = " + totalWaitTime);

                outputFile.println("Process id = " + currentlyRunning.getProcessID() + "\n\t Priority = " + currentlyRunning.getPriority() + "\n\t Arrival = " + currentlyRunning.getArrivalTime() +
                        "\n\t Duration = " + currentlyRunning.getDuration());
                 PriorityUpdate(outputFile,output,waitTime,currentlyRunning);
                outputFile.println("Process " + currentlyRunning.getProcessID() + " finished at time " + currentTime);
            }
            currentTime++;

            if (currentTime == finishTime && running == true) {
                running = false;


            }
        }


        while (!output.isEmpty()) {
            if (!running) {
                running = true;
                currentlyRunning = output.poll();
                waitTime = currentTime - currentlyRunning.getArrivalTime();
                totalWaitTime += waitTime;

                finishTime = currentTime + currentlyRunning.getDuration();
                outputFile.println("Process removed from queue is: id = " + currentlyRunning.getProcessID() + " at time " + currentTime + ","
                        + " wait time = " + waitTime + "Total wait time = " + totalWaitTime);

                outputFile.println("Process id = " + currentlyRunning.getProcessID() + "\n\t Priority = " + currentlyRunning.getPriority() + "\n\t Arrival = " + currentlyRunning.getArrivalTime() +
                        "\n\t Duration = " + currentlyRunning.getDuration());
                PriorityUpdate(outputFile,output,waitTime,currentlyRunning);
                outputFile.println("Process " + currentlyRunning.getProcessID() + " finished at time " + currentTime);
            }
            currentTime++;


            if (currentTime == finishTime && running) {
                running = false;
            }



        }
        outputFile.println("Process" + currentlyRunning.getProcessID() + " finished at time " + finishTime + "\n");
        outputFile.println("Total wait time = " + totalWaitTime);
        meanOfWait = totalWaitTime/10;
        outputFile.println("Average wait time = " + meanOfWait);
        outputFile.close();

    }

    private static void PriorityUpdate(PrintWriter file, PriorityQueue<ProcessStructure> queue, int indexWait, ProcessStructure input) {
        file.println("Update priority");
        if(queue.size() > 0 ){
            for(ProcessStructure newP : queue){
                if(indexWait > 30 && newP.getPriority() > 1){
                    int currentP = input.getPriority();
                    file.println("PID = " + input.getProcessID() + " wait time = " + indexWait + " current priority = " + input.getPriority());
                    input.setPriority(input.getPriority() - 1);
                    file.println("PID = " + input.getProcessID() + " wait time = " + input.getPriority());
                }
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
         PriorityQueue<ProcessStructure> priorityQueue = new PriorityQueue<>();
         ArrayList<ProcessStructure> process = new ArrayList<>();
         PrintWriter outputFile = new PrintWriter("process_scheduling_output.txt");


        Scanner fileInput = new Scanner(new File("InputData.txt"));
        while(fileInput.hasNext()){
            String[] processData = fileInput.nextLine().split(" ");
            ProcessStructure temp = new ProcessStructure();
            temp.setProcessID(Integer.parseInt(processData[0]));
            temp.setPriority(Integer.parseInt(processData[1]));
            temp.setDuration(Integer.parseInt(processData[2]));
            temp.setArrivalTime(Integer.parseInt(processData[3]));
            process.add(temp);
        }
        fileInput.close();
        stimulate(process, priorityQueue, outputFile);
    }




    }

