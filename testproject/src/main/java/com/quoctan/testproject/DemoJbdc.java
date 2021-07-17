package com.quoctan.testproject;


import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;


public class DemoJbdc {
    public static void main(String[] agrs) throws ClassNotFoundException, SQLException {
        // Phải add depedency trong pom.xml trước, xong nạp driver để sử dụng jbdc
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded!");
        
        // Tạo đối tượng connection
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/saledb", 
                "dev", 
                "P@ssw0rd");
        System.out.println("DB connected!");
        
        // Xong các bước trên mới có thể truy vấn
        
        
//        // Sử dụng statement chèn thêm 1 dòng dữ liệu
//        Statement stm = connection.createStatement();
//        int result = stm.executeUpdate("INSERT INTO `saledb`.`category` (`name`, `description`) VALUES ('Đồng hồ thông minh', 'Thiết bị di động thông minh');");
//        // Kết quả chèn nếu là số nguyên là OK, chèn thành công
//        System.out.println(result);
        

//        // Truy vấn (lấy) dữ liệu
//        Statement stm = connection.createStatement();
//        // Kết quả trả về là kiểu ResultSet
//        ResultSet result = stm.executeQuery("SELECT * FROM category");
//        // Duyệt dữ liệu trả về (đi tiến next())
//        while(result.next()) {
//            // Trong db kiểu gì thì ngoài đây get kiểu đấy
//            int id = result.getInt("id");
//            String name = result.getString("name");
//            System.out.printf("%d - %s\n", id, name);
//        }
        

//        // Sử dụng prepareStatement để truy vấn mà có nhu cầu sử dụng đối số
//        // do sử dụng nối chuỗi rất nguy hiểm (SQL injection), đây là interface
//        // của Statement nên cấu trúc khá tương đồng
//        // Sử dụng dấu ? cho đối số sau đó truyền vào sau
//        String sql = "SELECT * FROM product WHERE name LIKE CONCAT('%', ?, '%')";
//        PreparedStatement stm = connection.prepareStatement(sql);
//        // Truyền đối số vào vị trí ? tương ứng (đếm từ 1)
//        stm.setString(1, "iPhone");
//        // Gọi thực thi thôi không cần gõ lại query nữa
//        ResultSet result = stm.executeQuery();
//        while(result.next()) {
//            System.out.printf(
//                    "%d: %s - %.2f\n",
//                    result.getInt("id"),
//                    result.getString("name"),
//                    result.getDouble("price"));
//        }
        

//        // Callable statement
//        // Sử dụng call một procedure, dấu ? để truyền đối số vào
//        CallableStatement stm = connection.prepareCall("{call countCategory(?)}");
//        // Ở vị trí số 1 đối số có kiểu int
//        stm.registerOutParameter(1, Types.INTEGER);
//        stm.execute();
//        // Trong stm này có lưu luôn giá trị trả về rồi
//        System.out.println("Số lượng category: " + stm.getInt(1));
        
        // Callable statement với IN (truyền dữ liệu vào procedure)
//        CallableStatement stm = connection.prepareCall("{call getCateById(?)}");
//        // Vị trí số 1 id số 3
//        stm.setInt(1, 3);
//        // Kết quả trả về một result set sau khi query
//        ResultSet result = stm.executeQuery();
//        while(result.next()) {
//            System.out.printf("%d - %s\n", result.getInt("id"), result.getString("name"));
//        }
        

//        // Giao tác (transaction) gom các lệnh sql thành một đơn vị xử lý, nếu
//        // có lệnh thực thi bị lỗi thì sẽ không tác động đến csdl, ngược lại thì
//        // commit các thay đổi (được cache) xuống csdl. Ứng dụng trong các hệ
//        // thống có khả năng xảy ra mâu thuẫn như rút tiền, đặt vé xe,...
//        Statement stm = connection.createStatement();
//        try {
//            // Tắt tự động commit đi (mặc định true)
//            connection.setAutoCommit(false);
//            // Các câu truy vấn thành công hết thì khối lệnh mới được commit
//            stm.executeUpdate("INSERT INTO `saledb`.`category` (`name`, `description`) VALUES ('Đồng hồ thông minh 1', 'Thiết bị di động thông minh 1');");
//            stm.executeUpdate("INSERT INTO `saledb`.`category` (`name`, `description`) VALUES ('Đồng hồ thông minh 2', 'Thiết bị di động thông minh 2');");
//            // Commit (thực thi) dưới csdl
//            connection.commit();
//        } catch(SQLException ex) {
//            System.out.println(ex.getMessage());
//            // Nếu có lỗi xảy ra thì không thay đổi gì cả
//            connection.rollback();
//        }

        // Xử lý theo batch (lô). Transaction như trên là hệ thống thực hiện
        // từng câu sql (tốn thời gian load driver, tạo kết nối,...), còn batch
        // thực hiện nguyên cả một khối lệnh chỉ trong một lần nạp driver, tạo
        // nối. Tăng hiệu suất khi xử lý một lượng lớn dữ liệu.
        Statement stm = connection.createStatement();
        if(connection.getMetaData().supportsBatchUpdates()) {
            try {
                // Thêm sql bằng .addBatch(sql)
                stm.addBatch("INSERT INTO `saledb`.`category` (`name`, `description`) VALUES ('Đồng hồ thông minh 2', 'Thiết bị di động thông minh 2');");
                stm.addBatch("INSERT INTO `saledb`.`category` (`name`, `description`) VALUES ('Đồng hồ thông minh 3', 'Thiết bị di động thông minh 3');");
                // Lệnh này sẽ gom các câu sql lại thành 1 lệnh duy nhất thực
                // thi dưới csdl
                stm.executeBatch();
                connection.setAutoCommit(false);
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
                connection.rollback();
            }
        }
        
        // Thực thi xong nhớ đóng lại theo thứ tự
        stm.close();
        connection.close();
    }
}
