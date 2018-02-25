package com.aburubban.halalfoodfinder.Model;

/**
 * Created by Abu Rubban on 25/2/2561.
 */

public class Food {
    private String Name,Image,Description,Price,Discont,MenuId;

    public Food(){
    }

    public Food(String name, String image, String description, String price, String discont, String menuId) {
        Name = name;
        Image = image;
        Description = description;
        Price = price;
        Discont = discont;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscont() {
        return Discont;
    }

    public void setDiscont(String discont) {
        Discont = discont;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
