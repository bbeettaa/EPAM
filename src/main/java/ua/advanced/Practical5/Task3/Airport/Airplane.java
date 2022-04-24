package ua.advanced.Practical5.Task3.Airport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Airplane implements Runnable {
    public final int id;
    public final int passengerCapacity;
    Semaphore semaphore;
    List<Passenger> passengers;
    private final Airport airport;

    public Airplane(int id, int passengerCapacity, Airport airport) {
        this.id = id;
        this.passengerCapacity = passengerCapacity;
        this.semaphore = new Semaphore(passengerCapacity, true);
        this.passengers = new ArrayList<>();
        this.airport = airport;
    }

    public Airplane(int id, int passengerCapacity, Airport airport, List<Passenger> passengers) {
        this(id, passengerCapacity, airport);
        for (var passenger : passengers) {
            if (semaphore.tryAcquire())
                this.passengers.add(passenger);
        }
    }

    public Passenger getPassenger() {
        if(semaphore.availablePermits()==passengerCapacity) return null;
        semaphore.release();
        Passenger passenger = passengers.get(0);
        passengers.remove(passenger);
        return passenger;
    }

    synchronized public void setPassengers(Passenger passenger) {
        this.passengers.add(passenger);
        if (passengerCapacity == passengers.size()) {
            System.out.println(this + " place is " + passengers.size() + " / " + passengerCapacity);
        }
    }

    @Override
    public String toString() {
        return "Airplane{id=" + id + ", places " + this.passengerCapacity + ", available " + semaphore.availablePermits() + "}";
    }

    @Override
    public void run() {
        // System.err.println(this + " is wait for 5 seconds");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        airport.takeoff(this);

    }
}
