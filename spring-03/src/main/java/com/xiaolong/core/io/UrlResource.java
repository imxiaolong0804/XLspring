package com.xiaolong.core.io;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 14:34
 */
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        Preconditions.checkArgument(url != null, "url must not be null");
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (IOException e) {
            if (urlConnection instanceof HttpURLConnection httpURLConnection) {
                httpURLConnection.disconnect();
            }
            throw e;
        }
    }
}
