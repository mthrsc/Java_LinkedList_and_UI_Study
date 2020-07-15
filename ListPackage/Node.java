/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listPackage;

import AppPackage.SongObject;

/**
 *
 * @author mth
 * @param <SongObject>
 */
public class Node<T extends SongObject> {   

    SongObject object;  //node object
    Node next;  //next pointer

    public Node(SongObject elem) {  //Overriden constructor
        object = elem;
        next = null;
    }
    public Node() {     //Empty constructor used when I need a temporary node
        object = null;
        next = null;
    }
}
