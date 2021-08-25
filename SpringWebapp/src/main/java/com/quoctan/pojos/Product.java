package com.quoctan.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="product")
@JsonRootName(value = "products")
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min=5, max=100, message="{product.name.lenErr}")
    private String name;
    private String description;
    @Min(value=10000, message="{product.price.minErr}")
    @Max(value=10000000, message="{product.price.minErr}")
    private BigDecimal price;
    private String image;
    @Column(name="created_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createdDate;
    private boolean active;
    // Lazy thì khi đụng đến nó mới query, mặc định 1-n là eager -> query join
    // bảng mặc dù có thể không dùng tới
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    @NotNull(message="{product.category.nullErr}")
    // Không cho truy xuất từ api
    @JsonIgnore
    private Category category;
    // n-n fetch mặc định lazy
    @ManyToMany
    @JoinTable(
            name = "pro_man",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "manufacturer_id") }
    )
    @JsonIgnore
    private Set<Manufacturer> manufacturers;
    // Multipart liên kết file vào đường dẫn, gắn @transient để chỉ định trường 
    // ko có hiển thị dưới csdl
    @Transient
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
