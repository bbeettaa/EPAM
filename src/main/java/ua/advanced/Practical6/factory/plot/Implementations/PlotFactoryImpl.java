package ua.advanced.Practical6.factory.plot.Implementations;

import ua.advanced.Practical6.factory.plot.Interfaces.Named;
import ua.advanced.Practical6.factory.plot.Interfaces.Plot;
import ua.advanced.Practical6.factory.plot.Interfaces.PlotFactory;

public class PlotFactoryImpl implements PlotFactory {
    Plot plot;

    public PlotFactoryImpl(String story, Named hero, Named beloved, Named villain) {
        story = story.replace("%0", hero.name());
        story = story.replace("%1", beloved.name());
        story = story.replace("%2", villain.name());
        this.plot = new PlotImpl(story);
    }

    //"%1 threatens the world. But brave %01, brave %02, brave %03, brave %04,
    // brave %05, brave %06 on guard. So, no way that intrigues of %2 overcome the willpower of inflexible heroes"
    public PlotFactoryImpl(String story, Named[] heroes, Named beloved, Named villain) {
        String heroesDescribe = "";
        for (int i = 0; i < heroes.length; i++) {
            if (i == 0)
                heroesDescribe += "brave " + heroes[i].name();
            else heroesDescribe += ", brave " + heroes[i].name();
        }

        story = story.replace("%0", heroesDescribe);
        story = story.replace("%1", beloved.name());
        story = story.replace("%2", villain.name());
        this.plot = new PlotImpl(story);
    }

    @Override
    public Plot plot() {
        return plot;
    }
}
