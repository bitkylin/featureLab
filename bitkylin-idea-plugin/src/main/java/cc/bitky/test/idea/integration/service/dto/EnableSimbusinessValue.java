package cc.bitky.test.idea.integration.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author limingliang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnableSimbusinessValue {
    private Integer version;
    private String value;
    private Boolean defStatus;
}
