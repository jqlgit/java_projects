package binaryheap_proj;


public class Main {

    public static void main(String[] args) {

        MaxBinHeapER transfer = new MaxBinHeapER(makePatients());
        Prioritized[] arr = transfer.getAsArray();
        for(int i = 0; i < transfer.size(); i++) {
            System.out.println("Value: " + arr[i].getValue()
                    + ", Priority: " + arr[i].getPriority());
        }

    }

    public static void fillER(MaxBinHeapER complexER) {
        for(int i = 0; i < 100000; i++) {
            complexER.enqueue(i);
        }
    }
    public static void fillER(SimpleEmergencyRoom simpleER) {
        for(int i = 0; i < 100000; i++) {
            simpleER.addPatient(i);
        }
    }

    public static Patient[] makePatients() {
        Patient[] patients = new Patient[10];
        for(int i = 0; i < 10; i++) {
            patients[i] = new Patient(i);
        }
        return patients;
    }

    public static double[] compareRuntimes() {
        // Array which you will populate as part of Part 4
        double[] results = new double[4];

        SimpleEmergencyRoom simplePQ = new SimpleEmergencyRoom();
        fillER(simplePQ);

        // Code for (1) Here
        long startTimeSimp = System.nanoTime();
        for (int i = 0; i < 100000; i++){
            simplePQ.addPatient(1);
        }
        long endTimeSimp = System.nanoTime();
        long timeTotalSimp = endTimeSimp - startTimeSimp;
        results[0] = timeTotalSimp;

        results[1] = (double) timeTotalSimp / 100000;
        MaxBinHeapER binHeap = new MaxBinHeapER();
        fillER(binHeap);

        // Code for (2) Here
        long startTimeHeap = System.nanoTime();
        for (int j = 0; j < 100000; j++){
            binHeap.dequeue();
        }
        long endTimeHeap = System.nanoTime();

        long timeTotalHeap = endTimeHeap - startTimeHeap;
        results[2] = timeTotalHeap;
        results[3] = (double) timeTotalHeap / 100000;
        return results;
    }



}
