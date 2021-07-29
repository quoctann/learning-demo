// Cấu hình persistent class
package HibernateDemo.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// Annotation để hibernate hiểu class này đại diện cho bảng nào dưới csdl
@Entity
@Table(name="category")
public class Category implements Serializable{
    // Implements Serializable hoạt động trên internet để đồng bộ dữ liệu trên
    // đấy và desirialize ở máy local
    // Chỉ định đây là khóa chính, INDENTITY tương ứng trường tự tăng dưới csdl,
    // column là tên cột dưới csdl
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    // Từ hibernate 5 trở lên nếu tên thuộc tính giống tên trường thì không cần
    // chỉ định bằng @Column nữa
    //@Column(name = "name")
    private String name;
    //@Column(name = "description")
    private String description;
    // Mặc định fetch là lazy, nhờ liên kết ntn nên ta có thể truy xuất product
    // từ category (liệt kê tất cả sản phẩm có trong 1 danh mục nào đó), do nếu
    // set one to many eager thì kq query ra sẽ có sự trùng lắp dữ liệu đáng kể
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
    
    
    // Getter & setter
    
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
    
}
