package cn.lanedy.entity;


import java.io.Serializable;

/**
 * 使用@Field注解，保证实体类属性名称和Solr索引库中定义的Field域名称对应，
 * 如果当前属性名称和Solr索引库域Field名称相同，就添加@Field名称，如果不
 * 相同就添加@Field("域名称")注解
 * Created by liyue
 * Time 2019-03-04 17:16
 */
public class Goods implements Serializable {
    private Long id; //商品ID
    private String title; //商品标题
    private String price; //商品价格
    private String image; //商品图片
    private String category; //商品类别
    private String brand; //商品品牌
    private String seller; //商品卖家

    public Goods() {
    }

    public Goods(Long id, String title, String price, String category, String brand, String seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.seller = seller;
    }

    public Goods(Long id, String title, String price, String image, String category, String brand, String seller) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.image = image;
        this.category = category;
        this.brand = brand;
        this.seller = seller;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
