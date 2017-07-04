import java.util.*;
import java.io.*;

// write your matric number here: A0139024M
// write your name here: Tan Hong Yu
// write list of collaborators here: Clarence Chee
// year 2017 hash code: oIxT79fEI2IQdQqvg1rx (do NOT delete this line)

class EmergencyRoom {

    private BinaryHeap ward;

    public EmergencyRoom() {
        ward = new BinaryHeap();
    }

    void ArriveAtHospital(String patientName, int emergencyLvl) {
        ward.Insert(new Patient(patientName, emergencyLvl));
    }

    void UpdateEmergencyLvl(String patientName, int incEmergencyLvl) {
        ward.updateEmergency(patientName, incEmergencyLvl);
    }

    void Treat(String patientName) {
        ward.remove(patientName);
    }

    String Query() {
        String ans = "The emergency room is empty";

        if(ward.isEmpty())
            return ans;
        else{
            return ward.peekMax().getName();
        }
    }

    void run() throws Exception {
        // do not alter this method

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numCMD = Integer.parseInt(br.readLine()); // note that numCMD is >= N
        while (numCMD-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch (command) {
            case 0: ArriveAtHospital(st.nextToken(), Integer.parseInt(st.nextToken())); break;
            case 1: UpdateEmergencyLvl(st.nextToken(), Integer.parseInt(st.nextToken())); break;
            case 2: Treat(st.nextToken()); break;
            case 3: pr.println(Query()); break;
            }
        }
        pr.close();
    }

    public static void main(String[] args) throws Exception {
        // do not alter this method
        EmergencyRoom ps1 = new EmergencyRoom();
        ps1.run();
    }
}

class Patient {
    static int index = 1;
    private String name;
    private int eLevel;
    private int indexNumber;

    public Patient(String name, int eLevel){
        this.name = name;
        this.eLevel = eLevel;
        indexNumber = index++;
    }

    public void update(int incE) {
        eLevel += incE;
    }

    public String getName() {
        return name;
    }

    public int getELevel() {
        return eLevel;
    }

    public int getIndex() {
        return indexNumber;        
    }

}

class BinaryHeap {
    private Vector<Patient> A;
    private int BinaryHeapSize;
    
    //To store Patient Name as key and index on array as value
    private HashMap<String,Integer> patientList;

    BinaryHeap() {
        A = new Vector<Patient>();
        A.add(new Patient("",0)); // dummy
        BinaryHeapSize = 0;
        patientList = new HashMap<String,Integer>();
    }

    int parent(int i) { return i>>1; } // shortcut for i/2, round down

    int left(int i) { return i<<1; } // shortcut for 2*i

    int right(int i) { return (i<<1) + 1; } // shortcut for 2*i + 1

    void shiftUp(int i) {
        while (i > 1) {
            if(A.get(parent(i)).getELevel() < A.get(i).getELevel()) {
                Patient temp = A.get(i);
                A.set(i, A.get(parent(i)));
                A.set(parent(i), temp);
                updateHash(i, parent(i));
                i = parent(i); 
            }
            else if (A.get(parent(i)).getELevel() == A.get(i).getELevel()
                    && A.get(parent(i)).getIndex() > A.get(i).getIndex()) {
                Patient temp = A.get(i);
                A.set(i, A.get(parent(i)));
                A.set(parent(i), temp);
                updateHash(i, parent(i));
                i = parent(i);
            }
            else 
                break;
        }
    }

    void Insert(Patient key) {
        BinaryHeapSize++;
        if (BinaryHeapSize >= A.size()) {
            A.add(key);
            patientList.put(key.getName(), BinaryHeapSize);
        }
        else {
            A.set(BinaryHeapSize, key);
            patientList.put(key.getName(), BinaryHeapSize);
        }
        shiftUp(BinaryHeapSize);
    }

    void shiftDown(int i) {
        while (i <= BinaryHeapSize) {
            int maxV = A.get(i).getELevel(), max_id = i;
            if (left(i) <= BinaryHeapSize && maxV < A.get(left(i)).getELevel()) { // compare value of this node with its left subtree, if possible
                maxV = A.get(left(i)).getELevel();
                max_id = left(i);
            }

            else if (left(i) <= BinaryHeapSize && maxV == A.get(left(i)).getELevel()) {
                if(A.get(left(i)).getIndex() < A.get(max_id).getIndex()){
                    maxV = A.get(left(i)).getELevel();
                    max_id = left(i);
                }
            }
            if (right(i) <= BinaryHeapSize && maxV < A.get(right(i)).getELevel()) { // now compare with its right subtree, if possible
                maxV = A.get(right(i)).getELevel();
                max_id = right(i);
            }

            else if (right(i) <= BinaryHeapSize && maxV == A.get(right(i)).getELevel()) {
                if(A.get(right(i)).getIndex() < A.get(max_id).getIndex()){
                        maxV = A.get(right(i)).getELevel();
                        max_id = right(i);
                }
            }        

            if (max_id != i) {
                Patient temp = A.get(i);
                A.set(i, A.get(max_id));
                A.set(max_id, temp);
                updateHash(i, max_id);
                i = max_id;
            }
            else
                break;
        }
    }

    private void updateHash(int i, int max_id) {
        patientList.replace(A.get(max_id).getName(), max_id);
        patientList.replace(A.get(i).getName(), i);       
    }
    
    public void remove(String patientName) {
        int index = patientList.get(patientName);
        patientList.remove(patientName);
        A.set(index, A.get(BinaryHeapSize));
        patientList.replace(A.get(BinaryHeapSize).getName(), index);
        BinaryHeapSize--;
        shiftDown(index);
        shiftUp(index);
        
    }

    Patient peekMax() {
        Patient maxV = A.get(1);
        return maxV;
    }

    int size() { return BinaryHeapSize; }

    boolean isEmpty() { return BinaryHeapSize == 0; }
    
    //Updates Patient Emergency Level and updates the array accordingly
    void updateEmergency(String patientName, int incEmergencyLvl) {
        if(incEmergencyLvl > 0){
            int index = patientList.get(patientName);
            A.get(index).update(incEmergencyLvl);
            shiftUp(index);
//            shiftDown(index);
        }
    }
    
}