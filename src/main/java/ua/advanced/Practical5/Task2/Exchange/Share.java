package ua.advanced.Practical5.Task2.Exchange;

public class Share {
    private int quotation;
    private int price;
    String company;

    public Share(int quotation, int price, String company) {
        this.quotation = quotation;
        this.price = price;
        this.company=company;
    }

    public int getQuotation() {
        return quotation;
    }

    public void setQuotation(int quotation) {
        this.quotation = quotation;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Share{ quotation=" + quotation + ", price=" + price + ", company="+company+'}';
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
