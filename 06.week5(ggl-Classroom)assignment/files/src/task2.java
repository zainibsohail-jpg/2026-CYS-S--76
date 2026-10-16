
class Product {
    private String name;
    private int price;
    private int quantity;

    static int totalProduct = 0;

    Product() {
        name = "Unknown";
        price = 0;
        quantity = 0;
        counter();
    }

    Product(String n, int p, int q) {
        name = n;
        price = p;
        quantity = q;
        counter();
    }

    void getData() {
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
    }

    static void counter() {
        totalProduct++;
    }

    static int countProduct() {
        return totalProduct;
    }

    public static void main(String[] args) {
        Product p1 = new Product("Pen", 50, 2);
        Product p2 = new Product("Book", 200, 3);
        Product p3 = new Product("Bag", 1000, 1);
        Product p4 = new Product();
        Product p5 = new Product();

        p1.getData();

        System.out.println("Total Products: "
                + Product.countProduct());
    }
}
