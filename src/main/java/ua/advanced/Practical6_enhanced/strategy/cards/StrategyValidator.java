package ua.advanced.Practical6_enhanced.strategy.cards;

import ua.advanced.Practical6_enhanced.strategy.cards.interfaces.CardDealingStrategy;

public class StrategyValidator {
    public static boolean validate(CardDealingStrategy strategy) throws IllegalAccessException {
        return isRangeFieldObject(strategy);
    }

    private static boolean isRangeFieldObject(CardDealingStrategy strategy) throws IllegalAccessException {
        Class<?> clazz = strategy.getClass();
        for (var field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Range.class)) {
                field.setAccessible(true);
                Range range = field.getAnnotation(Range.class);
                int value = (int)field.get(strategy);
                if (value < range.min() || value>range.max())
                    throw new IllegalArgumentException("Cannot validate strategy because value is out of range. Field: "+field.getName());
            }
        }
        return true;
    }
    /*private static void notNullObject(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        for (var field : clazz.getFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
            }
        }
    }*/

}
