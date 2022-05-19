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
    public void testTotalOrderPrice() throws BillException {
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

    @Test
    public void testMoreThan5ProcessorsOneIsHalfPrice() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 7;
        String[] itemsName = { "P1", "P2", "P3", "P4", "P5", "P6", "KB1" };
        ElementsType[] types = {
            ElementsType.Processor,
            ElementsType.Processor,
            ElementsType.Processor,
            ElementsType.Processor,
            ElementsType.Processor,
            ElementsType.Processor,
            ElementsType.Keyboard
        };
        double[] prices = { 6D, 4D, 5D, 5D, 7D, 2D, 8D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        double res = 0;
        try {
            res = eShop.getOrderPrice(items, user);
        } catch (BillException e) {
            e.printStackTrace();
        }

        assertNotEquals(37D, res, 0);
        assertEquals(36D, res, 0);
    }

    @Test
    public void testMoreThan10MousesOneIsFree() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 14;
        String[] itemsName = { "M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10", "M11", "M12", "P1", "P2" };
        ElementsType[] types = {
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Processor,
            ElementsType.Processor
        };
        double[] prices = { 1D, 5D, 10D, 5D, 4D, 6D, 12D, 8D, 2D, 8D, 7D, 3D, 2D, 10D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        double res = 0;
        try {
            res = eShop.getOrderPrice(items, user);
        } catch (BillException e) {
            e.printStackTrace();
        }

        assertNotEquals(83D, res, 0);
        assertEquals(82D, res, 0);
    }

    @Test
    public void testSameMousesAndKeyboardsOneIsFree() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 6;
        String[] itemsName = { "M1", "M2", "KB1", "P1", "MB1", "KB2" };
        ElementsType[] types = {
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Keyboard,
            ElementsType.Processor,
            ElementsType.Motherboard,
            ElementsType.Keyboard
        };
        double[] prices = { 5D, 1D, 5D, 7D, 3D, 10D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        double res = 0;
        try {
            res = eShop.getOrderPrice(items, user);
        } catch (BillException e) {
            e.printStackTrace();
        }

        assertNotEquals(31D, res, 0);
        assertEquals(26D, res, 0);
    }

    @Test
    public void testTotalPriceMoreThan1000Get10PercentDiscount() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 2;
        String[] itemsName = { "MB1", "MB2" };
        ElementsType[] types = {
            ElementsType.Motherboard,
            ElementsType.Motherboard
        };
        double[] prices = { 700D, 400D };
        for (int i = 0; i < N; i++) {
            items.add(new EItem(types[i], itemsName[i], prices[i]));
        }

        double res = 0;
        try {
            res = eShop.getOrderPrice(items, user);
        } catch (BillException e) {
            e.printStackTrace();
        }

        assertNotEquals(1100D, res, 0);
        assertEquals(990D, res, 0);
    }

    @Test(expected = BillException.class)
    public void testMoreThan30ElementsException() throws BillException {
        User user = new User(1, "Mario", "Rossi", 30);
        List<EItem> items = new ArrayList<EItem>();
        EShop eShop = new EShop();

        int N = 6;
        String[] itemsName = { "M1", "M2", "KB1", "P1", "MB1", "KB2" };
        ElementsType[] types = {
            ElementsType.Mouse,
            ElementsType.Mouse,
            ElementsType.Keyboard,
            ElementsType.Processor,
            ElementsType.Motherboard,
            ElementsType.Keyboard
        };
        double[] prices = { 5D, 1D, 5D, 7D, 3D, 10D };
        for(int j = 0; j < 50; j++)
            for (int i = 0; i < N; i++)
                items.add(new EItem(types[i], itemsName[i], prices[i]));

        eShop.getOrderPrice(items, user);
    }
}
