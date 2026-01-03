package cc.bitky.webserver.controller;

import cc.bitky.webserver.config.FileBasePathProperties;
import cc.bitky.webserver.dto.PackDownloadReq;
import cc.bitky.webserver.service.IFileOperationService;
import cc.bitky.webserver.service.IFilePackService;
import cc.bitky.webserver.service.FilePackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Controller 测试
 */
@DisplayName("Controller 测试")
@WebMvcTest(ImaginaryEnemyController.class)
class ImaginaryEnemyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IFileOperationService fileOperationService;

    @MockBean
    private IFilePackService filePackService;

    @MockBean
    private FileBasePathProperties fileBasePathProperties;

    @Autowired
    private ObjectMapper objectMapper;

    @TempDir
    Path tempDir;

    private String basePath;

    @BeforeEach
    void setUp() {
        basePath = tempDir.toString();
        when(fileBasePathProperties.getBasePath()).thenReturn(basePath);
    }

    @Test
    @DisplayName("GET /spy/list 应返回文件列表")
    void listFilesShouldReturnFileList() throws Exception {
        when(fileOperationService.listFiles(basePath)).thenReturn(List.of(
                new IFileOperationService.FileDetail("test.txt", basePath + "/test.txt", false, 100, System.currentTimeMillis())
        ));

        mockMvc.perform(get("/spy/list")
                        .param("dir", basePath))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("test.txt"));
    }

    @Test
    @DisplayName("GET /spy/list 无参数时应使用配置的基础路径")
    void listFilesShouldUseDefaultPathWhenNoParam() throws Exception {
        when(fileOperationService.listFiles(any())).thenReturn(List.of());

        mockMvc.perform(get("/spy/list"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("GET /spy/current 应返回当前路径")
    void getCurrentPathShouldReturnPath() throws Exception {
        mockMvc.perform(get("/spy/current"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.path").value(basePath));
    }

    @Test
    @DisplayName("POST /spy/pack 应返回 ZIP 文件")
    void packDownloadShouldReturnZipFile() throws Exception {
        String testFile = tempDir.resolve("test.txt").toString();
        new File(testFile).createNewFile();

        PackDownloadReq req = new PackDownloadReq();
        req.setPaths(List.of(testFile));

        File zipFile = tempDir.resolve("test.zip").toFile();
        zipFile.createNewFile();

        when(fileOperationService.validatePaths(any())).thenReturn(IFileOperationService.ValidationResult.success());
        when(filePackService.createEncryptedZip(any())).thenReturn(zipFile);
        when(filePackService.getZipPassword()).thenReturn(new FilePackService.PasswordInfo("test", false));

        mockMvc.perform(post("/spy/pack")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", org.hamcrest.Matchers.containsString(".zip")));
    }

    @Test
    @DisplayName("POST /spy/pack 请求体为空应返回 400")
    void packDownloadShouldReturn400WhenBodyEmpty() throws Exception {
        mockMvc.perform(post("/spy/pack")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("GET /spyGuy 应返回文件资源")
    void spyGuyShouldReturnFileResource() throws Exception {
        String fileName = "test.txt";
        Path filePath = tempDir.resolve(fileName);
        new File(filePath.toString()).createNewFile();

        when(fileOperationService.locateFile(basePath, fileName)).thenReturn(filePath);

        mockMvc.perform(get("/spyGuy")
                        .param("guy", fileName))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", org.hamcrest.Matchers.containsString(fileName)));
    }
}
