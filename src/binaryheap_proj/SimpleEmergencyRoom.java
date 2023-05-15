package binaryheap_proj;

import java.util.ArrayList;
import java.util.List;

public class SimpleEmergencyRoom {
    private List<Patient> patients;

    public SimpleEmergencyRoom() {
        patients = new ArrayList<>();
    }

    // TODO: dequeue
    public Patient dequeue() {
        if (this.patients.size() == 0) {
            return null;
        } else if (this.patients.size() == 1) {
            Patient fin = this.patients.get(0);
            this.patients.remove(0);
            return fin;
        } else {
            int top = (Integer) patients.get(0).getPriority();
            int i = 0;
            for (int j = 0; j < patients.size(); j++){
                if((Integer) patients.get(j).getPriority() < top){
                    top = (Integer) patients.get(j).getPriority();
                    i = j;
                }
            }
            return patients.remove(i);
        }
    }


    public <V, P> void addPatient(V value, P priority) {
        Patient patient = new Patient(value, (Integer) priority);
        patients.add(patient);
    }

    public <V> void addPatient(V value) {
        Patient patient = new Patient(value);
        patients.add(patient);
    }

    public List getPatients() {
        return patients;
    }

    public int size() {
        return patients.size();
    }

}