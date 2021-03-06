package shoes.entity;
// Generated Oct 2, 2019 5:01:25 PM by Hibernate Tools 4.3.1



/**
 * Billretail generated by hbm2java
 */
public class Billretail  implements java.io.Serializable {


     private int billretail;
     private Bill bill;
     private Product product;
     private int quantity;
     private Double price;
     private Double aman;

    public Billretail() {
    }

	
    public Billretail(int billretail, int quantity) {
        this.billretail = billretail;
        this.quantity = quantity;
    }
    public Billretail(int billretail, Bill bill, Product product, int quantity, Double price, Double aman) {
       this.billretail = billretail;
       this.bill = bill;
       this.product = product;
       this.quantity = quantity;
       this.price = price;
       this.aman = aman;
    }
    
    public Billretail(Product product, int quantity) {
       
       this.product = product;
       this.quantity = quantity;
    }
    public int getBillretail() {
        return this.billretail;
    }
    
    public void setBillretail(int billretail) {
        this.billretail = billretail;
    }
    public Bill getBill() {
        return this.bill;
    }
    
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getAman() {
        return this.aman;
    }
    
    public void setAman(Double aman) {
        this.aman = aman;
    }




}


