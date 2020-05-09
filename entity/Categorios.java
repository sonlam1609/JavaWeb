package shoes.entity;
// Generated Oct 2, 2019 5:01:25 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Categorios generated by hbm2java
 */
public class Categorios  implements java.io.Serializable {


     private int catId;
     private String catName;
     private Set products = new HashSet(0);

    public Categorios() {
    }

	
    public Categorios(int catId, String catName) {
        this.catId = catId;
        this.catName = catName;
    }
    public Categorios(int catId, String catName, Set products) {
       this.catId = catId;
       this.catName = catName;
       this.products = products;
    }
   
    public int getCatId() {
        return this.catId;
    }
    
    public void setCatId(int catId) {
        this.catId = catId;
    }
    public String getCatName() {
        return this.catName;
    }
    
    public void setCatName(String catName) {
        this.catName = catName;
    }
    public Set getProducts() {
        return this.products;
    }
    
    public void setProducts(Set products) {
        this.products = products;
    }




}

