////////////////////////////////////////////////////////////////////
// [IVAN ANTONINO] [ARENA] [2000546]
// [SEBASTIEN] [BIOLLO] [1223855]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.ElementsType;
import it.unipd.mtss.business.exception.BillException;

import java.util.List;
import java.time.LocalTime;

public class EShop implements Bill {

    public EShop() {
    }

    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        if (itemsOrdered == null) {
            throw new BillException("La lista e' null");
        }

        if (itemsOrdered.size() == 0) {
            throw new BillException("Non ci sono elementi nell'ordinazione");
        }

        for (EItem item : itemsOrdered) {
            if (item.getPrice() < 0) {
                throw new BillException("Un elemento ha un prezzo negativo");
            }
        }

        double res = 0;
        for (EItem item : itemsOrdered) {
            res += item.getPrice();
        }

        return res;
    }
}
