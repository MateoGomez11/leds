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

    public void middleLed() throws InterruptedException {
        NodeDE temp = head;
        if (head != null) {
            int cont = 0;
            int middle = size / 2;
            while (cont < middle) {
                temp = temp.getNext();
                cont++;
            }
            if (size % 2 != 0) {
                temp.getData().setDateOn(LocalTime.now());
                temp.getData().setState(true);
                Thread.sleep(1000);
                temp.getData().setDateOff(LocalTime.now());
                temp.getData().setState(false);
                if (temp.getNext() != null && temp.getPrevious() != null) {
                    NodeDE previousNode = temp.getPrevious();
                    NodeDE nextNode = temp.getNext();
                    startAndStopTime(previousNode, nextNode);
                }
            } else{
                NodeDE previousNode = temp.getPrevious();
                NodeDE nextNode = temp;
                startAndStopTime(previousNode, nextNode);
            }
        }
    }

    private void startAndStopTime(NodeDE previousNode, NodeDE nextNode) throws InterruptedException {
        while (previousNode != null && nextNode != null) {
            previousNode.getData().setDateOn(LocalTime.now());
            nextNode.getData().setDateOn(LocalTime.now());
            previousNode.getData().setState(true);
            nextNode.getData().setState(true);
            Thread.sleep(1000);
            previousNode.getData().setDateOff(LocalTime.now());
            nextNode.getData().setDateOff(LocalTime.now());
            previousNode.getData().setState(false);
            nextNode.getData().setState(false);
            previousNode = previousNode.getPrevious();
            nextNode = nextNode.getNext();
        }
    }


}
