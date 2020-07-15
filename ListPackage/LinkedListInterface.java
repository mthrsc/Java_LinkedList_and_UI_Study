/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListPackage;

import AppPackage.SongObject;

/**
 *
 * @author mth
 */
public interface LinkedListInterface {

    void add(SongObject object);

    void add(int index, SongObject object);

    void editTitle(int index, String newTitle);

    void editYear(int index, int year);

    SongObject get(int index);

    boolean isEmpty();

    void printList();

    void remove(int index);

    int searchTitle(String title);

    void shuffle();

    int size();

    void sortTitleASC();

    void sortTitleDSC();

    void sortYearASC(int listSize);

    void sortYearDSC(int listSize);
    
}
