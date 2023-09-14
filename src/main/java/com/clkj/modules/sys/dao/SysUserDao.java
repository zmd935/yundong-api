/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clkj.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    SysUserEntity queryByUserName(String username);

    /**
     * 根据用户id，查询当前用户的所属部门
     */
    String queryDepartmentNameByUserId(long userId);

    /**
     * 查询是否有子部门
     *
     * @param deptId 部门id
     * @return 当前部门的所有子部门ID
     */
    List<Long> queryDeptIdByparentId(long deptId);

    /**
     * 通过部门id查询该部门下的所有员工
     *
     * @param deptId 部门id
     * @param page   分页起始数
     * @param limit  页面显示大小
     * @return 该部门下的所有员工信息
     */
    List<SysUserEntity> queryUserByDeptId(@Param("deptId") long deptId, @Param("page") int page,
                                          @Param("limit") int limit);

    /**
     * 查询总数
     *
     * @param deptId 部门id
     * @param page   分页起始数
     * @param limit  每页显示数
     * @return 该部门下的所有员工数量
     */
    int totalCountByDeptId(@Param("deptId") long deptId, @Param("page") int page, @Param("limit") int limit);
}
