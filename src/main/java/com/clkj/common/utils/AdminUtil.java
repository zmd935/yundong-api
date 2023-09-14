package com.clkj.common.utils;

/**
 * @author Created by YangLiu on 2021-04-22
 */
public class AdminUtil {
    public static boolean isNotAdmin(Long id) {
        return !(isAdmin(id));
    }

    public static boolean isAdmin(Long id) {
        return id != null && Constant.SUPER_ADMIN.contains(id);
    }
}
