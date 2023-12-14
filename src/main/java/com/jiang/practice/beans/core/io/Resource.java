package com.jiang.practice.beans.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
