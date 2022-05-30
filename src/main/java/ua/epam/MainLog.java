package ua.epam;

import org.apache.log4j.Logger;

public class MainLog {
    final static Logger logger = Logger.getLogger(MainLog.class);

    public static void main(String[] args) {

        MainLog obj = new MainLog();
        obj.log();

    }

    private void log(){

        if(logger.isDebugEnabled()){
            logger.debug("This is debug log..");
        }

        if(logger.isInfoEnabled()){
            logger.info("This is info  log ...");
        }

        logger.warn("This is warn log ...");
        logger.error("This is error log... ");
        logger.fatal("This is fatal log ...");

    }
}
