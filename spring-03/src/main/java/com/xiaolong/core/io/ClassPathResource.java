package com.xiaolong.core.io;

import com.google.common.base.Preconditions;
import com.xiaolong.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 11:36
 */
public class ClassPathResource implements Resource {

    private final String path;

    private final ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Preconditions.checkArgument(path != null, "path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        Preconditions.checkNotNull(inputStream, "class path resource [" + path + "] not found");
        return inputStream;
    }
}
