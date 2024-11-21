
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {
    private static int availablePlaces;
    private int currentOccupied = 0;
    public ParkingLot(int av_places){availablePlaces=av_places;}
    public static boolean check_if_there_avPlaces(){return availablePlaces>0;}
    public static synchronized boolean Car_In(int id,int gateNum, int arriveTime,int waitedTime)
    {
        if(check_if_there_avPlaces()) {
            availablePlaces--;
            int Parking_Status = 4 - availablePlaces;

            if (waitedTime == 0) {
                System.out.println("car " + id + " from gate " + gateNum + " arrived at time " + arriveTime);
                System.out.println("car " + id + " from gate " + gateNum + " Parked " + "( Parking Status: " + (Parking_Status-1) + " spots left )");
            }
            else {
                System.out.println("car " + id + " from gate " + gateNum + " parked after waiting for " + waitedTime + " of time");
            }
            return true;
        }
        else{
            System.err.println("car " + id +" from gate "+ gateNum +" waiting for a spot ");
        }       return false;}



    public static synchronized void Car_Out(int id,int gateNum,int Duration){
        availablePlaces++;
        int Parking_Status = 4 - availablePlaces;
        System.out.println("car " + id +" from gate"+ gateNum+" left after "+ Duration +" seconds "+"(Parking Status: " + (Parking_Status+1) + " spots occupied");
    }
    public static int getCurrentOccupiedSlots(List<ParkingLot> parkingLots) {
        int totalOccupied = 0;
        for (ParkingLot lot : parkingLots) {
            totalOccupied += lot.currentOccupied;
        }
        return totalOccupied;
    }

    public void incrementOccupied() {
        this.currentOccupied++;
    }

    public void decrementOccupied() {
        this.currentOccupied--;
    }

}