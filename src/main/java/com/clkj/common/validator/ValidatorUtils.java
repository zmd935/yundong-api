/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.clkj.common.validator;

import com.clkj.common.exception.RRException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * hibernate-validator校验工具类
 * <p>
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author Mark sunlightcs@gmail.com
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RRException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws RRException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new RRException(msg.toString());
        }
    }

    /**
     * 校验对象
     *
     * @param list   待校验集合
     * @param groups 待校验的组
     * @throws RRException 校验不通过，则报RRException异常
     */
    public static void validateList(List<?> list, Class<?>... groups) throws RRException {

        Iterator<?> it = list.iterator();
        int count = 1;
        List<String> errList = new ArrayList<>();
        while (it.hasNext()) {
            Object obj = it.next();
            Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
            if (!constraintViolations.isEmpty()) {
                StringBuilder msg = new StringBuilder();
                msg.append(count).append(":{");
                for (ConstraintViolation<Object> constraint : constraintViolations) {
                    msg.append(constraint.getMessage()).append("<br>");
                }
                msg.append("}");
                errList.add(msg.toString());
            }
            count++;
        }

        if (errList.size() > 0) {
            throw new RRException(StringUtils.join(errList, ","));
        }
    }
}
