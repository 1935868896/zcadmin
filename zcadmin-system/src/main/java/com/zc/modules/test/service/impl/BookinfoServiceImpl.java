package com.zc.modules.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zc.modules.test.entity.Bookinfo;
import com.zc.modules.test.mapper.BookinfoMapper;
import com.zc.modules.test.service.IBookinfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangC
 * @since 2021-08-02
 */
@Service
public class BookinfoServiceImpl extends ServiceImpl<BookinfoMapper, Bookinfo> implements IBookinfoService {

}
