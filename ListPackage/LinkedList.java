/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listPackage;

import java.util.Random;
import AppPackage.SongObject;

/**
 *
 * @author mth
 * @param <T>
 *
 */
// I use T extends SongObject in order to be able to access the getter and setter of the object class
public class LinkedList<T extends SongObject> implements LinkedListInterface {

    private Node<SongObject> first;     //First and last node for LinkedList
    private Node<SongObject> last;
    private Random rand = new Random();     //Using random class for shuffle function

    public int size() {
        int i = 0;

        if (first == null) {        //If first node is null list is empty
            return 0;
        } else {
            Node curr = first;      //Start at first node and go through the list until we reach the end, adding 1 to size at every hop
            do {
                i++;
                curr = curr.next;
            } while (curr != null);

            return i;
        }
    }

    public boolean isEmpty() {
        boolean i = false;

        if (first == null) {    //If first is null, then list is empty
            i = true;
        } else {
            i = false;
        }
        return i;
    }

    public void add(SongObject object) {        //If list is empty then add node at first, otherwise at last.next
        Node newNode = new Node(object);
        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = last.next;       //Update of last node for future add
        }
    }

    public void add(int index, SongObject object) { //add at index
        if (index < 0 || index >= size()) {     // If index is out of bound, throw exception
            throw new IndexOutOfBoundsException();
        }
        Node<SongObject> n = new Node<>(object);
        if (index == 0) {       //if index = 0, replace first node
            first.object = n.object;
            if (first.next == null) {
                last = first;       //update last
            }
        } else {
            Node curr = first;      //if index > 0, we cycle through the list and stop just before the index
            for (int i = 1; i < index; i++) {
                curr = curr.next;
            }
            curr.next.object = n.object;    //We can then work on curr.next and change its object
            if (curr.next.next == null) {
                last = curr.next;
            }
        }
    }

    public void remove(int index) {

        if (index < 0 || index >= size()) {     // If index is out of bound, throw exception
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {       //If index = 0 set first.next (second) as first
            if (first.next == null) {   //Unless it is null and the list is now empty
                last = first = null;
            } else {
                first = first.next;
            }
        } else {        //Else cycle through list and stop before index
            Node<SongObject> curr = first;
            for (int i = 1; i < index; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;     //curr.next will no point at the second object after itself, letting curr.next invisible from now on
            if (curr.next == null) {
                last = curr;
            }
        }
    }

    public SongObject get(int index) {      //Return object of type SoundObject, which later let me access its setter and getter
        SongObject object = null;       //Returning a node rather than the object would have made things more difficult since Gui.java doesn't know about nodes, but knows about SongObjects
        Node curr = first;

        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i <= index - 1; i++) {
            curr = curr.next;
        }

        object = (SongObject) curr.object;
        return object;
    }

    //Suffle use 2 random numbers, source and destination.
    //each number will be used to point at indexes in the list
    //each object in each indexes will swap position
    public void shuffle() {
        SongObject tempObject;
        int i, k;
        if (size() > 0) {
            for (int j = 0; j < 50; j++) {
                i = rand.nextInt(size());
                tempObject = get(i);
                do {
                    k = rand.nextInt(size());
                } while (k == i);       //Keeps generating until k!=i to avoid putting item into original place

                add(i, get(k));     //Overwritting objects in nodes
                add(k, tempObject);
                tempObject = null;
            }
        }
    }

    public void printList() {       //Going through list and calling Overriden toString method in classObject
        for (int i = 0; i < size(); i++) {
            System.out.println(get(i));
        }
    }

    //Going through list with for loop and changing object data via setter
    public void editTitle(int index, String newTitle) {

        Node curr = new Node();
        curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.object.setTitle(newTitle);
    }

    //Going through list with for loop and changing object data via setter
    public void editYear(int index, int year) {

        Node curr = new Node();
        curr = first;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        curr.object.setYear(year);
    }

    
    //Sorting is using recursion to move at the bottom of the list the object with the "biggest" year
    //When recursion starts, the list size is reduce by 1, ignoring the item that has just been moved down
    public void sortYearDSC(int listSize) {
        if (listSize == 0) {
            return;
        }
        int biggest = 0;
        int biggestIndex = 0;
        Node temp = new Node();

        for (int i = 0; i < listSize; i++) {
            if (get(i).getYear() > biggest) {
                biggest = get(i).getYear();
                biggestIndex = i;
            } else if (get(i).getYear() == biggest) {
                biggest = get(i).getYear();
                biggestIndex = i;
            } else {
                biggest = biggest;
                biggestIndex = biggestIndex;
            }
        }
        temp.object = get(biggestIndex);
        remove(biggestIndex);

        add(temp.object);
        sortYearDSC(listSize - 1);
    }

    //Same as above except for "smallest" year.
    //int smallest is set to 9999 in order to find something smaller than it. My App will need an update in the years 10,000s
    public void sortYearASC(int listSize) {
        if (listSize == 0) {
            return;
        }
        int smallest = 9999;
        int smallestIndex = 0;
        Node temp = new Node();

        for (int i = 0; i < listSize; i++) {
            if (get(i).getYear() < smallest) {
                smallest = get(i).getYear();
                smallestIndex = i;
            } else if (get(i).getYear() == smallest) {
                smallest = get(i).getYear();
                smallestIndex = i;
            } else {
                smallest = smallest;
                smallestIndex = smallestIndex;
            }
        }

        temp.object = get(smallestIndex);

        remove(smallestIndex);

        add(temp.object);
        sortYearASC(listSize - 1);
    }

    //In order to sort strings I am using compareToIgnoreCase which return an int. For DSC sorting, the lower the int is
    //the bigger the difference is between the 2 strings. From there, I can decide which object to push at the bottom of the list
    //The recursion stops when no more moving is being done, it is tracked with swapAmount.
    public void sortTitleDSC() {
        int swapAmount = 0;

        SongObject temp1 = new SongObject();
        SongObject temp2 = new SongObject();

        for (int i = 0; i < size() - 1; i++) {
            if (get(i).getTitle().compareToIgnoreCase(get(i + 1).getTitle()) < 0) {
                temp1 = get(i);
                temp2 = get(i + 1);
                add(i, temp2);
                add(i + 1, temp1);
                swapAmount++;
            }
        }
        if (swapAmount > 0) {
            sortTitleDSC();
        }
    }

    //Same as above but for ASC sorting. Here we want the numeral difference between 2 strings to be higher than 0
    public void sortTitleASC() {
        int swapAmount = 0;

        SongObject temp1 = new SongObject();
        SongObject temp2 = new SongObject();

        for (int i = 0; i < size() - 1; i++) {
            if (get(i).getTitle().compareToIgnoreCase(get(i + 1).getTitle()) > 0) {
                temp1 = get(i);
                temp2 = get(i + 1);
                add(i, temp2);
                add(i + 1, temp1);
                swapAmount++;
            }
        }
        if (swapAmount > 0) {
            sortTitleASC();
        }
    }

    //For the search function, I am still using compareToIgnoreCase, and wait to find 0 as a result.
    //The downside of that method is that it only recognizes perfect matches
    public int searchTitle(String title) {
        int indexValue = 0;

        for (int i = 0; i < size(); i++) {
            if (get(i).getTitle().compareToIgnoreCase(title) == 0) {
                indexValue = i;
            } else {
                if (indexValue > 9) {
                    indexValue = 9999;
                } else {
                    indexValue = indexValue;
                }
            }
        }
        return indexValue;
    }
}
