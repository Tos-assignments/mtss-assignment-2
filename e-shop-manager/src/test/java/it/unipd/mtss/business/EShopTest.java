////////////////////////////////////////////////////////////////////
// [IVAN ANTONINO] [ARENA] [2000546]
// [SEBASTIEN] [BIOLLO] [1223855]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import it.unipd.mtss.model.ElementsType;
import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EShopTest {

    @Test
    public void testGetOrderPrice() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 5;
        String[] itemsName = { "M1", "M2", "P1", "MB1", "KB1" };
        ElementsType[] types = {
                ElementsType.Mouse,
                ElementsType.Mouse,
                ElementsType.Processor,
                ElementsType.Motherboard,
                ElementsType.Keyboard,
        };
        double[] prices = { 4D, 6D, 9D, 3D, 7D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        double res = 0;
        try {
            res = eShop.getOrderPrice(items, user);
        } catch (BillException e) {
            e.printStackTrace();
        }

        assertEquals(29D, res, 0);
    }

    @Test(expected = BillException.class)
    public void testEmptyList() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        eShop.getOrderPrice(items, user);
    }

    @Test(expected = BillException.class)
    public void testNullList() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = null;
        EShop eShop = new EShop();

        eShop.getOrderPrice(items, user);
    }

    @Test(expected = BillException.class)
    public void testNegativePrices() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 5;
        String[] itemsName = { "M1", "M2", "P1", "MB1", "KB1" };
        ElementsType[] types = {
                ElementsType.Mouse,
                ElementsType.Mouse,
                ElementsType.Processor,
                ElementsType.Motherboard,
                ElementsType.Keyboard,
        };
        double[] prices = { 4D, 6D, -10D, 3D, 7D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        eShop.getOrderPrice(items, user);
    }
}
