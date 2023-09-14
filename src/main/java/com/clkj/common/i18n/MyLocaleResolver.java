package com.clkj.common.i18n;

import com.clkj.common.utils.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * 自定义国际化语言解析器
 *
 * @author chen yongsong
 * @className MyLocaleResolver
 * @date 2019/9/15 16:59
 */


public class MyLocaleResolver implements LocaleResolver {
    private static final String I18N_LANGUAGE = "i18n-language";
    public static final String I18N_LANGUAGE_SESSION = "i18n-language-session";
    public static final Locale DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;

    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        String i18nLanguage = req.getHeader(I18N_LANGUAGE);
        Locale locale = DEFAULT_LOCALE;
        if (!StringUtils.isEmpty(i18nLanguage)) {
            String[] language = i18nLanguage.split("_");
            locale = new Locale(language[0], language[1]);

            //将国际化语言保存到session
            HttpSession session = req.getSession();
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        } else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = req.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest req, HttpServletResponse res, Locale locale) {
        System.out.println("1111");
    }
}
