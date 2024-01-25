package com.lin.springboot3demo.controller;

import com.lin.springboot3demo.data.Author;
import com.lin.springboot3demo.data.Book;
import com.lin.springboot3demo.entity.BookInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("graphql")
public class GraphQLController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        System.out.println(id);
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.authorId());
    }

    @MutationMapping
    public Boolean deleteById(@Argument String id){
        return Book.deleteById(id);
    }

    @MutationMapping
    public BookInput updateById(@Argument BookInput book){
        return Book.updateById(book);
    }
    @QueryMapping
    public List<Book> books(){
        return Book.getBooks();
    }
}
