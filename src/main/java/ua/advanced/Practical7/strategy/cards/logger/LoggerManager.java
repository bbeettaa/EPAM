package ua.advanced.Practical7.strategy.cards.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class LoggerManager {
    public static Logger logger = Logger.getLogger(LoggerManager.class.getSimpleName());
    static {
        try {
            Handler fh = new FileHandler("practical7.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            //logger.addHandler(new ConsoleHandler());
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
