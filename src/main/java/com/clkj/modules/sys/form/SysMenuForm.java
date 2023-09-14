package com.clkj.modules.sys.form;

import com.clkj.modules.sys.entity.SysMenuEntity;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author Created by YangLiu on 2022-03-29
 */
@Data
public class SysMenuForm {
    private List<SysMenuEntity> menuList;

    private Set<String> permissions;
}
