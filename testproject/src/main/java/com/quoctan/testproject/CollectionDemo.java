package com.quoctan.testproject;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


// Một số phương thức hữu ích hay dùng đối với danh sách
public class CollectionDemo {
    public static void main(String[] args) {
//        List<Integer> sampleList = Arrays.asList(4, 5, 6, 7, 8, 10);
//        
//        // Sắp xếp theo chiều giảm dần (reverse, bỏ đi thì là tăng dần)
//        Collections.sort(sampleList, Collections.reverseOrder()); 
//        sampleList.forEach(value -> System.out.printf("%d\t", value));
//        
//        // Giá trị lớn nhất giá trị nhỏ nhất
//        System.out.println("\nMax = " + Collections.max(sampleList));
//        System.out.println("\nMin = " + Collections.min(sampleList));
//        
//        // Trộn danh sách một cách ngẫu nhiên
//        Collections.shuffle(sampleList);
//        sampleList.forEach(value -> System.out.printf("%d\t", value));
//        
//        // Tần suất xuất hiện của một đối tượng nào đó trong danh sách
//        int sampleNumber = 2;
//        System.out.println("\nFreq = " +
//                Collections.frequency(sampleList, sampleNumber));
//        
//        // Tạo một danh sách với các giá trị giống nhau cho trước
//        Collections.nCopies(9, 10).forEach(value -> 
//                System.out.printf("%d\t", value));

//        List<Integer> sampleList = new LinkedList<>();
//        // Mỗi lần thêm một phần tử
//        sampleList.add(10);
//        sampleList.add(9);
//        // Mỗi lần thêm nhiều phần tử
//        sampleList.addAll(Arrays.asList(5, 6, 7));
//        sampleList.forEach(value -> System.out.printf("%d\t", value));
//        sampleList.contains(5); // true
//        sampleList.indexOf(99); // -1
//        sampleList.lastIndexOf(7); // 4

        // Ví dụ về Map
        Map<String, PhanSo> m = new HashMap<>();
        m.put("a", new PhanSo(2, 3));
        m.put("b", new PhanSo(4, 3));
        // Vì key là duy nhất cho nên khi put lần nữa nó sẽ ghi đè cái cũ
        m.put("a", new PhanSo(9, 10));
        
        m.keySet().forEach(v -> System.out.printf("%s\t", v));
        m.values().forEach(p -> System.out.printf("%s\t", p));
        System.out.printf("\n%s\n", m.get("a")); // 2/3
    
        //Map sẽ trả về đối tượng kiểu BiConsumber, xem import để biết thêm
        m.forEach((key, value) -> {
            System.out.printf("Key = %s, Value = %s\n", key, value);
        });
    }     
}

class PhanSo {
    private int tuSo, mauSo;

    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        this.mauSo = mauSo;
    }

    @Override
    public String toString() {
        return String.format("%d/%d", tuSo, mauSo);
    }
    
    
    
}