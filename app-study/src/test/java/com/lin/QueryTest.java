package com.lin;

import com.lin.entities.Book;
import com.lin.entities.Device;
import com.lin.entities.Owner;
import com.lin.entities.User;
import com.lin.entities.table.DeviceTableDef;
import com.lin.entities.table.UserTableDef;
import com.lin.entities.vo.OwnerVo;
import com.lin.entities.vo.UserVo;
import com.lin.services.BookService;
import com.lin.services.DeviceService;
import com.lin.services.OwnerService;
import com.lin.services.UserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.lin.entities.table.BookTableDef.BOOK;
import static com.lin.entities.table.DeviceTableDef.DEVICE;
import static com.lin.entities.table.OwnerTableDef.OWNER;
import static com.lin.entities.table.UserTableDef.USER;

@SpringBootTest
public class QueryTest {

    @Resource
    private UserService userService;

    @Resource
    private BookService bookService;


    @Test
    void initData() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");
        user.setAge(18);
        user.setName("name1");

        Book book = new Book();
        book.setName("Java 编程思想");
        book.setAuthorId(user.getId());
        bookService.save(book);

        user = new User();
        user.setId(2);
        user.setEmail("test2@example.com");
        user.setAge(25);
        user.setName("name2");

        book = new Book();
        book.setName("Effective Java");
        book.setAuthorId(user.getId());
        bookService.save(book);
    }

    @Test
    void testQuery() {
        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select(USER.ID, USER.NAME, USER.ALL_COLUMNS)
                .select(BOOK.ID, BOOK.NAME, BOOK.ALL_COLUMNS)
                .join(BOOK)
                .on(BOOK.AUTHOR_ID.eq(UserTableDef.USER.ID));

        List<UserVo> list = userService.listAs(queryWrapper, UserVo.class);
        System.out.println(list);
    }

    @Resource
    private DeviceService deviceService;

    @Resource
    private OwnerService ownerService;

    @Test
    void initData2() {
        Owner owner = new Owner();
        owner.setName("owner1");

        Device device = new Device();
        device.setName("device1");
        deviceService.save(device);
        owner.setFirstId(device.getId());

        device = new Device();
        device.setName("device2");
        deviceService.save(device);
        owner.setSecondId(device.getId());

        ownerService.save(owner);

        owner = new Owner();
        owner.setName("owner2");

        device = new Device();
        device.setName("device3");
        deviceService.save(device);
        owner.setFirstId(device.getId());

        device = new Device();
        device.setName("device4");
        deviceService.save(device);
        owner.setSecondId(device.getId());

        ownerService.save(owner);
    }

    @Test
    void testQuery2() {
        // 在vo中使用两个同类型的实体字段无法完成完成映射
        DeviceTableDef d1 = DEVICE.as("d1");
        DeviceTableDef d2 = DEVICE.as("d2");
        QueryWrapper queryWrapper = QueryWrapper.create()
                                                .select(OWNER.ALL_COLUMNS)
                                                .select(d1.ID.as("first.id"), d1.NAME.as("first.name"))
                                                .leftJoin(d1)
                                                .on(OWNER.FIRST_ID.eq(d1.ID))
                                                .leftJoin(d2)
                                                .on(OWNER.SECOND_ID.eq(d2.ID));
        List<OwnerVo> ownerVos = ownerService.listAs(queryWrapper, OwnerVo.class);
        System.out.println(ownerVos);
    }

    @Test
    void testQuery3() {
        // 针对testQuery2的问题 使用ResultMap完成映射
        List<OwnerVo> ownerDevice = ownerService.getOwnerDevice();
        System.out.println(ownerDevice);
    }

    @Test
    void testQuery4() {
        // 使用关联关系查询
        List<Owner> listRelations = ownerService.getListRelations();
        System.out.println(listRelations);
    }

    @Test
    void testJoinPageQuery() {
        QueryWrapper queryWrapper = QueryWrapper
                .create()
                .select(USER.ID, USER.NAME, USER.ALL_COLUMNS)
                .select(BOOK.ID, BOOK.NAME, BOOK.AUTHOR_ID)
                .join(BOOK)
                .on(BOOK.AUTHOR_ID.eq(UserTableDef.USER.ID));
        // join 查询以User为主表，会导致数据数量偏多
        Page<UserVo> page = Page.of(1, 10);
        Page<UserVo> userVoPage = userService.pageAs(page, queryWrapper, UserVo.class);
        System.out.println(userVoPage.getRecords());
        System.out.println("pageNumber: " + userVoPage.getPageNumber());
        System.out.println("pageSize: " + userVoPage.getPageSize());
        System.out.println("totalPage: " + userVoPage.getTotalPage());
        System.out.println("totalRow: " + userVoPage.getTotalRow());
    }

    @Test
    void testRelatedQuery() {
        // 这是正确的分页数据
        Page<User> userPage = userService.pageRelationsQuery(1, 10);
        System.out.println(userPage.getRecords());
        System.out.println("pageNumber: " + userPage.getPageNumber());
        System.out.println("pageSize: " + userPage.getPageSize());
        System.out.println("totalPage: " + userPage.getTotalPage());
        System.out.println("totalRow: " + userPage.getTotalRow());
    }

    @Test
    void testChainQuery() {
        List<User> users = userService.queryChain()
                                      .from(USER)
                                      .list();
        System.out.println(users);
    }

    @Test
    void testQuery5(){
        User user = userService.getOne(QueryCondition.create(USER.ID, 2));
        System.out.println(user);
    }

    @Test
    void testAsQuery(){
        List<Book> list = bookService.list();
        System.out.println(list);
    }

}
