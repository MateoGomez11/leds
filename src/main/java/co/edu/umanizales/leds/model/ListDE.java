package co.edu.umanizales.leds.model;

import lombok.Data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private int size;

    public List<Led> getLeds() {
        List<Led> leds = new ArrayList<>();
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                leds.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return leds;
    }

    public void add(Led led) {
        NodeDE newNodeDE = new NodeDE(led);
        if (head != null) {
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            if (size != 1) {
                temp.getData().setState(false);
                temp.getData().setDateOff(LocalTime.now());
            }
            newNodeDE.getData().setState(true);
            newNodeDE.getData().setDateOn(LocalTime.now());
            temp.setNext(newNodeDE);
            newNodeDE.setPrevious(temp);

        } else {
            head = new NodeDE(led);
            head.getData().setState(true);
            head.getData().setDateOn(LocalTime.now());
        }
        size++;
    }

    public void addToStart(Led led) {
        NodeDE newNodeDE = new NodeDE(led);
        if (head != null) {
            head.getData().setState(false);
            head.getData().setDateOff(LocalTime.now());
            head.setPrevious(newNodeDE);
            newNodeDE.setNext(head);
            head = newNodeDE;
        } else {
            head = new NodeDE(led);
            head.setPrevious(null);
        }
        head.getData().setState(true);
        head.getData().setDateOn(LocalTime.now());
        size++;
    }

    public void resetLeds() {
        NodeDE temp = head;
        if (head != null) {
            while (temp != null) {
                temp.getData().setState(false);
                temp.getData().setDateOff(LocalTime.now());
                temp = temp.getNext();
            }
        }
    }


}
