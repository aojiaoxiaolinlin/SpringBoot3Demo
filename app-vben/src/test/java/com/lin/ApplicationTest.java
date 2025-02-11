package com.lin;

import com.lin.entities.Role;
import com.lin.entities.User;
import com.lin.entities.UserRole;
import com.lin.mappers.UserMapper;
import com.lin.services.RoleService;
import com.lin.services.UserRoleService;
import com.lin.services.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.lin.entities.table.RoleTableDef.ROLE;
import static com.lin.entities.table.UserRoleTableDef.USER_ROLE;
import static com.lin.entities.table.UserTableDef.USER;

@SpringBootTest
public class ApplicationTest {

    @Resource
    private UserService userService;

    @Test
    public void userSave() {
        User user = new User();
        user.setAccount("linlin");
        user.setPassword("123456");
        user.setUsername("linlin");
        user.setRealName("linlin");

        userService.save(user);
    }

    @Test
    void userRoleQuery() {
        User user = userService.login("vben", "123456");
        if (user != null) {
            System.out.println(user);
        }
    }

    @Test
    void userQuery() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.where(USER.ACCOUNT.eq("vben"))
                    .and(USER.PASSWORD.eq("123456"));
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

    @Resource
    private UserMapper userMapper;

    @Test
    void userQuery2() {
        User user = userMapper.selectUserRoleById("01JKDG9E74MPN3FGZF5GZY7CH3");
        System.out.println(user);
    }

    @Test
    void userQuery3() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                                                .from(USER)
                                                .join(USER_ROLE).on(USER.ID.eq(USER_ROLE.USER_ID))
                                                .join(ROLE).on(ROLE.ID.eq(USER_ROLE.ROLE_ID))
                                                .where(USER.ID.eq("01JKDG9E74MPN3FGZF5GZY7CH3"));
        User user = userService.getOne(queryWrapper);
        System.out.println(user);
    }

    @Resource
    private RoleService roleService;

    @Test
    void roleSave() {
        Role role = new Role();
        role.setRoleName("admin");
        roleService.save(role);
    }

    @Resource
    private UserRoleService userRoleService;

    @Test
    void userRoleSave() {
        UserRole userRole = new UserRole("01JKF6HDTXN5YXT894K996A6XM", "01JKF3P2K1C2X6DYRFNSH6QJK1");
        userRoleService.save(userRole);
    }
}
