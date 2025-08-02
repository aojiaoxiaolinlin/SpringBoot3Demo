package com.lin.entities.vo;

import com.lin.entities.Book;
import com.lin.entities.User;
import com.mybatisflex.annotation.TableRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableRef(User.class)
public class UserVo {
    private Integer id;

    private String name;

    private String email;

    private Integer age;

    private List<Book> books;
}
