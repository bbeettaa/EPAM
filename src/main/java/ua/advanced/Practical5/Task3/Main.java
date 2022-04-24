package ua.advanced.Practical5.Task3;

import ua.advanced.Practical5.Task3.Airport.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<RampPoint> ramps = new ArrayList<>() {
            {
                this.add(new Ramp(0));
                this.add(new Ramp(1));
                this.add(new Ramp(2));
                this.add(new Terminal(3));
                this.add(new Terminal(4));
                //this.add(new Terminal(5));
            }
        };
        Airport<RampPoint> airport = new Airport<>(ramps);
        new Thread(airport).start();

        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 10; i++){
         passengers.add(new Passenger(airport,i+50));
    }
        for (int i = 0; i < 20; i++) {
            new Passenger(airport,i).start();
        }

        airport.registryLandingAirplane(new Airplane(777,10,airport));
        airport.registryArrivingAirplane(new Airplane(333,15,airport,passengers));

    }
}
