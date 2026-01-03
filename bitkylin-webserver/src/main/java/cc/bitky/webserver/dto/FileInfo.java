package cc.bitky.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件信息 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    /**
     * 文件名
     */
    private String name;

    /**
     * 完整路径
     */
    private String path;

    /**
     * 是否目录
     */
    private boolean directory;

    /**
     * 文件大小（字节）
     */
    private long size;

    /**
     * 最后修改时间（时间戳）
     */
    private long lastModified;

}
