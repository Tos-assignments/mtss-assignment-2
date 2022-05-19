////////////////////////////////////////////////////////////////////
// [IVAN ANTONINO] [ARENA] [2000546]
// [SEBASTIEN] [BIOLLO] [1223855]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EItemTest {
    @Test
    public void test(){
        EItem item = new EItem(ElementsType.Keyboard, "Nafta", 3.0D);

        item.setItemType(ElementsType.Mouse);
        item.setName("Logitech");
        item.setPrice(50D);

        assertEquals(ElementsType.Mouse, item.getItemType());
        assertEquals("Logitech", item.getName());
        assertEquals(50D, item.getPrice(), 0);
    }
}
