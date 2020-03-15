export class Product {
    id: number;
    name: String;
    price: number;
    description: String;
    image: String;

    constructor(id: number, name: String, price: number, description: String, image: String) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
}