package com.lin.services.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.lin.entities.Book;
import com.lin.mappers.BookMapper;
import com.lin.services.BookService;
import org.springframework.stereotype.Service;

/**
 * 书 服务层实现。
 *
 * @author linlin
 * @since 2025-07-01
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>  implements BookService{

}
