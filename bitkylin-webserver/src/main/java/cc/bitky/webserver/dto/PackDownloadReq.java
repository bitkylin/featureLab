package cc.bitky.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 打包下载请求 DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackDownloadReq {

    /**
     * 要打包的文件/文件夹路径列表
     */
    private List<String> paths;

}
