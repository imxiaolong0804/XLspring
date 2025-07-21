package com.xiaolong.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 11:25
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
