//// Copy paste this Java Template and save it as "EmergencyRoom.java"
//import java.util.*;
//import java.io.*;
//
//// write your matric number here: A0139024M
//// write your name here: Tan Hong Yu
//// write list of collaborators here:
//// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)
//
//class EmergencyRoomA {
//    // if needed, declare a private data structure here that
//    // is accessible to all methods in this class
//    private ArrayList<Patient> patientList;
//
//    public EmergencyRoomA() {
//        patientList = new ArrayList<Patient>();
//    }
//
//    void ArriveAtHospital(String patientName, int emergencyLvl) {
//        // You have to insert the information (patientName, emergencyLvl)
//        // into your chosen data structure
//        //
//        // write your answer here
//        patientList.add(new Patient(patientName, emergencyLvl));
//
//    }
//
//    void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
//        // You have to update the emergencyLvl of patientName to
//        // emergencyLvl += incEmergencyLvl
//        // and modify your chosen data structure (if needed)
//        //
//        // write your answer here
//        for(Patient p : patientList){
//            if(patientName.equals(p.getName())){
//                p.update(incEmergencyLvl);
//                break;
//            }
//        }
//    }
//
//    void Treat(String patientName) {
//        // This patientName is treated by the doctor
//        // remove him/her from your chosen data structure
//        //
//        // write your answer here
//        for(Patient p : patientList){
//            if(patientName.equals(p.getName())){
//                patientList.remove(p);
//                break;
//            }
//        }
//
//
//    }
//
//    String Query() {
//        String ans = "The emergency room is empty";
//        int highest = 0;
//        String outputName = new String("");
//        if(patientList.isEmpty())
//            return ans;
//        else{
//            for(Patient p : patientList){
//                if(p.getELevel() > highest){
//                    highest = p.getELevel();
//                    outputName =  new String(p.getName());
//                }
//            }
//            return outputName;
//        }
//    }
//
//    void run() throws Exception {
//        // do not alter this method
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
//        int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
//        while (numCMD-- > 0) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int command = Integer.parseInt(st.nextToken());
//            switch (command) {
//            case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
//            case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
//            case 2: Treat(st.nextToken()); break;
//            case 3: pr.println(Query()); break;
//            }
//        }
//        pr.close();
//    }
//
//    public static void main(String[] args) throws Exception {
//        // do not alter this method
//        EmergencyRoom ps1 = new EmergencyRoom();
//        ps1.run();
//    }
//}
//
//class Patient {
//    static int index = 1; // Ranking the arrival time of the Patient
//    private String name;
//    private int eLevel;
//    private int indexNumber;
//
//    public Patient(String name, int eLevel){
//        this.name = name;
//        this.eLevel = eLevel;
//        indexNumber = index++;
//    }
//    
//    public void update(int incE){
//        eLevel += incE;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public int getELevel(){
//        return eLevel;
//    }
//
//    public int getIndex(){
//        return indexNumber;        
//    }
//
//}