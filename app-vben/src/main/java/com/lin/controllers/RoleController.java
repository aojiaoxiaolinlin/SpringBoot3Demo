package com.lin.controllers;

import com.lin.api.HttpResult;
import com.lin.entities.Role;
import com.lin.services.RoleService;
import com.mybatisflex.core.paginate.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色表 控制层。
 *
 * @author linlin
 * @since 2025-02-07
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 添加角色表。
     *
     * @param role 角色表
     *
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping
    public HttpResult<Boolean> save(@RequestBody Role role) {
        return HttpResult.success(roleService.save(role));
    }

    /**
     * 根据主键删除角色表。
     *
     * @param id 主键
     *
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("{id}")
    public HttpResult<Boolean> remove(@PathVariable String id) {
        return HttpResult.success(roleService.removeById(id));
    }

    /**
     * 根据主键更新角色表。
     *
     * @param role 角色表
     *
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping
    public HttpResult<Boolean> update(@RequestBody Role role) {
        return HttpResult.success(roleService.updateById(role));
    }

    /**
     * 查询所有角色表。
     *
     * @return 所有数据
     */
    @GetMapping
    public HttpResult<List<Role>> list() {
        return HttpResult.success(roleService.list());
    }

    /**
     * 根据角色表主键获取详细信息。
     *
     * @param id 角色表主键
     *
     * @return 角色表详情
     */
    @GetMapping("{id}")
    public HttpResult<Role> getInfo(@PathVariable String id) {
        return HttpResult.success(roleService.getById(id));
    }

    /**
     * 分页查询角色表。
     *
     * @param page 分页对象
     *
     * @return 分页对象
     */
    @GetMapping("page")
    public HttpResult<Page<Role>> page(Page<Role> page) {
        return HttpResult.success(roleService.page(page));
    }

}
