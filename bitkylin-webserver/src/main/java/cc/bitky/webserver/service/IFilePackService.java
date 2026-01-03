package cc.bitky.webserver.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件打包服务接口
 */
public interface IFilePackService {

    /**
     * 获取 ZIP 密码信息
     *
     * @return 密码信息
     */
    FilePackService.PasswordInfo getZipPassword();

    /**
     * 创建加密 ZIP 文件
     *
     * @param sourcePaths 源路径列表
     * @return ZIP 文件
     * @throws IOException  IO 异常
     */
    File createEncryptedZip(List<String> sourcePaths) throws IOException;
}
