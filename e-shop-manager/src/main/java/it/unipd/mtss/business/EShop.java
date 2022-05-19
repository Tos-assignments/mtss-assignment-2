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

        if(itemsOrdered.size() > 30) {
            throw new BillException("Troppi elementi nell'ordinazione");
        }

        for (EItem item : itemsOrdered) {
            if (item.getPrice() < 0) {
                throw new BillException("Un elemento ha un prezzo negativo");
            }
        }

        int nProcessors = 0;
        int indexLeastExpensiveProcessor = -1;
        int nMouses = 0;
        int indexLeastExpensiveMouse = -1;
        int nKeyboards = 0;
        int indexLeastExpensiveKeyboard = -1;
        
        for (int i = 0; i < itemsOrdered.size(); ++i) {
            ElementsType itemType = itemsOrdered.get(i).getItemType();
            if (itemType == ElementsType.Processor) {
                nProcessors++;
                if (indexLeastExpensiveProcessor == -1 || itemsOrdered.get(indexLeastExpensiveProcessor).getPrice() > itemsOrdered.get(i).getPrice()) {
                    indexLeastExpensiveProcessor = i;
                }
            } else if (itemType == ElementsType.Mouse) {
                nMouses++;
                if (indexLeastExpensiveMouse == -1
                        || itemsOrdered.get(indexLeastExpensiveMouse).getPrice() > itemsOrdered.get(i).getPrice()) {
                    indexLeastExpensiveMouse = i;
                }
            } else if (itemType == ElementsType.Keyboard) {
                nKeyboards++;
                if (indexLeastExpensiveKeyboard == -1
                        || itemsOrdered.get(indexLeastExpensiveKeyboard).getPrice() > itemsOrdered.get(i).getPrice()) {
                    indexLeastExpensiveKeyboard = i;
                }
            }
        }

        if (nProcessors > 5) {
            double leastExpensiveProcessorPrice = itemsOrdered.get(indexLeastExpensiveProcessor).getPrice();
            itemsOrdered.get(indexLeastExpensiveProcessor).setPrice(leastExpensiveProcessorPrice / 2);
        }

        if (nMouses > 10) {
            itemsOrdered.get(indexLeastExpensiveMouse).setPrice(0);
        }

        if (nKeyboards > 0 && nKeyboards == nMouses) {
            itemsOrdered.get(indexLeastExpensiveKeyboard).setPrice(0);
        }

        double res = 0;
        for (EItem item : itemsOrdered) {
            res += item.getPrice();
        }

        if (res > 1000) {
            res -= res*10/100;
        }

        return res;
    }
}
