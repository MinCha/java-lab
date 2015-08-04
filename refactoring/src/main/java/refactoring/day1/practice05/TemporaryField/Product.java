package refactoring.day1.practice05.TemporaryField;

public class Product {
    private int quantity;
    private int itemPrice;

    public Product(int quantity, int price) {
        this.quantity = quantity;
        this.itemPrice = price;
    }

    public double getPrice() {
        double basePrice = this.quantity * this.itemPrice;

        // 할인율 계산, 1000원 이상일 경우 5%할인 적용
        double discount = 0;

        if (basePrice > 1000) {
            discount = 0.95;
        } else {
            discount = 0.98;
        }

        return basePrice * discount;
    }

}