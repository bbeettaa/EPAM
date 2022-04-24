package ua.advanced.Practical5.Task3.Airport;


import java.util.ArrayList;
import java.util.List;

public class Airport<T extends RampPoint> implements Runnable {
    List<Airplane> landingAirplanes;
    List<Airplane> arrivingAirplanes;

    private List<T> resources;

    public Airport(List<T> resources) {
        this.resources = resources;
        this.landingAirplanes = new ArrayList<>();
        this.arrivingAirplanes = new ArrayList<>();
    }

    synchronized public T takeToAirplane(Passenger passenger) throws ResourceException, InterruptedException {
        while (true){
            Thread.sleep(100);
            for (int j = 0; j < landingAirplanes.size(); j++) {
                if (landingAirplanes.get(j).semaphore.tryAcquire()) {
                    for (int i = 0; i < resources.size(); i++) {
                        if (!resources.get(i).isBusy()) {//&& !landingAirplanes.get(j).isFull()) {
                            System.out.println("Passanger #" + passenger.id + " takeoff " + resources.get(i) + " to " + landingAirplanes.get(j));
                            landingAirplanes.get(j).setPassengers(passenger);
                            resources.get(i).setBusy(true);
                            return resources.get(i);
                        }
                    }
                    landingAirplanes.get(j).semaphore.release();
                }}
            }
    }

    public void landing(Airplane airplane) throws InterruptedException {
        Passenger passenger;
        RampPoint ramp = null;
        try {
            while (airplane.semaphore.availablePermits() != airplane.passengerCapacity)
                for (int i = 0; i < resources.size(); i++) {
                    if (!resources.get(i).isBusy()) {
                        ramp = resources.get(i);
                        ramp.setBusy(true);
                        passenger = airplane.getPassenger();
                        System.out.println("Passanger #" + passenger.id + " landing " + resources.get(i) + " from " + airplane);
                        ramp.using();
                        ramp.setBusy(false);
                    }
                }
        } catch (NullPointerException ex) {
        } finally {
            if(ramp!=null)
            ramp.setBusy(false);
        }
        System.out.println(airplane + " ready to take passenger and takeoff");
        arrivingAirplanes.remove(airplane);
        landingAirplanes.add(airplane);
        new Thread(airplane).start();
    }

    synchronized public void takeoff(Airplane airplane) {
        System.err.println(airplane + " is takeoff");
        landingAirplanes.remove(airplane);
    }

    public void registryLandingAirplane(Airplane airplane) {
        System.out.println("Registry airplane -> " + airplane);
        this.landingAirplanes.add(airplane);
        new Thread(airplane).start();
    }

    public void registryArrivingAirplane(Airplane airplane) {
        System.out.println("Registry arriving airplane -> " + airplane);
        Airport airport = this;
        this.arrivingAirplanes.add(airplane);
        try {
            Thread.sleep(3000);
            System.out.println("airplane " + airplane + " -> arriving");
            airport.landing(airplane);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


    }
}
