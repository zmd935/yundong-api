package com.clkj.common.enums.opt;

import com.clkj.common.exception.RRException;

/**
 * @author Created by YangLiu on 2020-12-21
 */
public enum TermsTypeEnum {

    /**
     * Company Profile
     */
    CompanyProfile(1, "Company Profile"),
    /**
     * Privacy & Terms
     */
    PrivacyTerms(2, "Privacy & Terms");

    private Integer value;
    private String label;

    TermsTypeEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static TermsTypeEnum resolve(Integer value) {
        for (TermsTypeEnum anEnum : values()) {
            if (anEnum.getValue().equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public static TermsTypeEnum valueOf(Integer value) {
        TermsTypeEnum anEnum = resolve(value);
        if (anEnum == null) {
            throw new RRException("No matching constant for [" + value + "]", true);
        }
        return anEnum;
    }
}
