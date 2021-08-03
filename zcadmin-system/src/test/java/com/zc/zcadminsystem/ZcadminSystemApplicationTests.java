package com.zc.zcadminsystem;

import com.zc.zcadminsystem.modules.test.entity.Book;
import com.zc.zcadminsystem.modules.test.entity.Bookinfo;
import com.zc.zcadminsystem.modules.test.mapper.BookMapper;
import com.zc.zcadminsystem.modules.test.service.IBookinfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZcadminSystemApplicationTests {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    IBookinfoService bookinfoService;

    @Test
    void contextLoads() {
//        Book book = bookMapper.selectByPrimaryKey(1);
        Bookinfo book = bookinfoService.getById(3);
        System.out.println(book.toString());
    }

}
