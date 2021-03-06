package shoes.entity;
// Generated Oct 2, 2019 5:01:25 PM by Hibernate Tools 4.3.1


import java.io.Serializable;

/**
 * Company generated by hbm2java
 */
public class Company  implements java.io.Serializable {


     private int companyId;
     private String companyName;
     private String addr;
     private String phone;
     private String email;

    public Company() {
    }

	
    public Company(int companyId, String addr) {
        this.companyId = companyId;
        this.addr = addr;
    }
    public Company(int companyId, String companyName, String addr, String phone, String email) {
       this.companyId = companyId;
       this.companyName = companyName;
       this.addr = addr;
       this.phone = phone;
       this.email = email;
    }
   
    public int getCompanyId() {
        return this.companyId;
    }
    
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getAddr() {
        return this.addr;
    }
    
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


