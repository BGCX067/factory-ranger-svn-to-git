/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

/**
 *
 * @author axioo
 */
public class Product {
    
    private static Product product = new Product();
    
    public static Product getSingleton(){
        return product;
    }
    
    //attribute
    private int price;
    private char name;
    private int machine;
    
    //constructor
    public Product() {}
    
    public Product(int machine, int price) {
        this.machine = machine;
        this.name = (char)(machine+64);
        this.price = price;
    }
    
    //getter setter
    public void setProduct(Product product){
        this.machine = product.machine;
        this.name = (char)product.machine;
        this.price = product.price;
    }
    
    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
