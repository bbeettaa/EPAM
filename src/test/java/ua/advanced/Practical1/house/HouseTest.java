package ua.advanced.Practical1.house;

import ua.advanced.Practical1.Task1.house.House;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HouseTest {

    @Test
    public void shouldCheckGenericTypeParameters() {
        Type[] houseTypes = House.class.getTypeParameters();
        assertEquals("You must parametrize the House class", 1 ,houseTypes.length);

        String houseType = houseTypes[0].getTypeName();
        try {
            Field field = House.class.getDeclaredField("residents");
            assertEquals("You must parametrize the House class",
                    "java.util.List<" + houseType + ">",
                    field.getGenericType().getTypeName());
        } catch (NoSuchFieldException e) {
            fail("'House' class should contain 'residents' field");
        }
    }
}