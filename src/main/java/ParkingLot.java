
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLot {
    private static int availablePlaces;

    public ParkingLot(int av_places){availablePlaces=av_places;}

    public static boolean check_if_there_avPlaces(){return availablePlaces>0;}

    public static synchronized boolean Car_In(int id,int gateNum, int arriveTime,int waitedTime)
    {
        if(check_if_there_avPlaces()) {
            availablePlaces--;
            int Parking_Status = 4 - availablePlaces;

            if (waitedTime == 0) {
                System.out.println("car " + id + " from " + gateNum + " arrived at time " + arriveTime);
                System.out.println("car " + id + " from " + gateNum + " Parked " + "(Parking Status: " + Parking_Status + " spots occupied");
            }
            else {
                System.out.println("car " + id + " from " + gateNum + " parked after waiting for " + waitedTime + " of time");
            }
            return true;
        }
        else{
            System.err.println("car " + id +" from "+ gateNum +" waiting for a spot ");
        }       return false;}



    public static synchronized void Car_Out(int id,int gateNum,int Duration){
        availablePlaces++;
        int Parking_Status = 4 - availablePlaces;
        System.out.println("car " + id +" from "+ gateNum+" left after "+ Duration +"(Parking Status: " + Parking_Status + " spots occupied");
    }

}