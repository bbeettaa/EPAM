package ua.advanced.Practical5.Task3.new1;


public class Passenger extends Thread {
    Airport<RampPoint> airport;
    int id;

    public Passenger(Airport<RampPoint> airport, int id) {
        this.airport = airport;
        this.id = id;
        System.out.println("Passenger #" + id + " -> initialised");
    }

    public void run() {
        RampPoint ramp = null;
        try {
            ramp = airport.takeToAirplane(this);
            ramp.using();
            ramp.setBusy(false);
            //airport.landing(this,ramp);
        } catch (ResourceException e) {
            System.err.println("Passenger #" + id + " lost ->" + e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
