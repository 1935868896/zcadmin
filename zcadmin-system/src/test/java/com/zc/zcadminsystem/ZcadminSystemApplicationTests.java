package com.zc.zcadminsystem;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.modules.test.entity.Book;
import com.zc.modules.test.mapper.BookMapper;
import com.zc.modules.test.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ZcadminSystemApplicationTests {
    @Autowired
    BookMapper bookMapper;
//    @Autowired
//    IBookinfoService bookinfoService;
    @Autowired
    BookService bookService;

    @Test
    void contextLoads() {
//        Book book = bookMapper.selectByPrimaryKey(1);
//        Bookinfo book = bookinfoService.getById(3);
//        System.out.println(book.toString());
    }

    @Test
    void bookTest(){
        //1. 查
        bookService.selectByPrimaryKey(1);


        //2. 批量插入
        Book book1=new Book();
        book1.setAuthor("123");
        bookService.insert(book1);

//        List<Book> books =new ArrayList<>();
//        for (int i = 0; i < 300; i++) {
//            Book book=new Book();
//            book.setName("测试书籍");
//            book.setAuthor(""+i);
//            books.add(book);
//        }
//        bookService.insertBatch(books);

        //3. 批量修改

        //4.批量查
        List<Integer> ids = Arrays.asList(1, 2, 3, 5, 9, 1);
        List<Book> books1 = bookService.selectByPrimaryKeys(ids);


        Book query=new Book();
        query.setPress("人民出版社");

        List<Book> books2 = bookService.selectListBySelective(query);
        Page page=new Page();
        page.setSize(10);
        page.setCurrent(1);
        List<OrderItem> orderItems=new ArrayList<>();
        OrderItem orderItem=new OrderItem();
        orderItem.setAsc(true);
        orderItem.setColumn("name");
        orderItems.add(orderItem);
        page.setOrders(orderItems);

        IPage<Book> bookIPage = bookService.selectPageBySelective(query,page);

        System.out.println("23");


    }

}
