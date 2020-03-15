package nl.hsleiden.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductModel extends BaseModel {
    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    double price;

    @Column(name = "product_description")
    String description;

    @Column(name = "product_image")
    String image;

    public ProductModel() {

    }

    public ProductModel(String name, double price, String description, String image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return this.image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
