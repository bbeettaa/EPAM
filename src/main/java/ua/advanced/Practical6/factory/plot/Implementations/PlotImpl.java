package ua.advanced.Practical6.factory.plot.Implementations;

import ua.advanced.Practical6.factory.plot.Interfaces.Plot;

public class PlotImpl implements Plot {
    String story;

    public PlotImpl(String story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return story;
    }
}
