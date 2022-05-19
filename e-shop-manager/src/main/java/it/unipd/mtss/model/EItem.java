////////////////////////////////////////////////////////////////////
// [IVAN ANTONINO] [ARENA] [2000546]
// [SEBASTIEN] [BIOLLO] [1223855]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem {
    private ElementsType itemType;
    private String name;
    private double price;

    public EItem(ElementsType itemType, String name, double price) {
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }

    public ElementsType getItemType() {
        return itemType;
    }

    public void setItemType(ElementsType itemType) {
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}