package cz.cvut.fel.pjv;
public class CircularArrayQueue implements Queue {
    int starter, end, length, size_fronta = 0;
    String items[] = new String[length];
    public CircularArrayQueue() {
        this.length = 5;
        starter = -1;
        end = -1;
        this.items = new String[length];
    }
    public CircularArrayQueue(int capacity) {
        this.length = capacity;
        starter = -1;
        end = -1;
        this.items = new String[length];
    }
    public int size() {
        return size_fronta;
    }
    public boolean isFull() {
        return starter == 0 && end == length - 1 || starter == end + 1;
    }
    public boolean isEmpty() {
        return starter == -1;
    }
    public boolean enqueue(String obj) {
        if (isFull()) {
            return false;
        } else {
            size_fronta = size_fronta + 1;
            if (starter == -1) {
                starter=0;
                end = 0;
            } else if (end == length - 1) {
                end = 0;
            }else if(end != -1 && end != length - 1){
                end += 1;
            }
            items[end] = obj;
        }
        return true;
    }
    public String dequeue() {
        String element;
        if (isEmpty()) {
            return null;
        } else {
            element = items[starter];
            if (starter == end) {
                starter = -1;
                end = -1;
            }
            else {
                if (starter == length - 1){
                    starter = 0;
                }else{
                    starter += 1;
                }
            }
            size_fronta = size_fronta - 1;
            return (element);
        }
    }
    public void printAllElements() {
        int ender = end+1;
        if(end < starter){
            for (int i = starter ; i < length ; i++){
                System.out.print(items[i] + "\n");
            }
            for (int i=0 ; i < ender; i++){
                System.out.print(items[i] + "\n");
            }
        }
        if(end > starter){
            for (int i = starter ; i < ender; i += 1) {
                System.out.print(items[i] + "\n");
            }
        }
        if(end == starter && end != -1){
            System.out.println(items[end]);
        }
    }
}