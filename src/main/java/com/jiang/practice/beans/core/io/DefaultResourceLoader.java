package com.jiang.practice.beans.core.io;

import java.net.MalformedURLException;
import java.net.URL;

import cn.hutool.core.lang.Assert;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));  // classPath 优先
        } else {
            try {
                URL url = new URL(location);  // URL 其次
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);  // 最后 File
            }
        }
    }

}
