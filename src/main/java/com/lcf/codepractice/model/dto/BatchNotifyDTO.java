package com.lcf.codepractice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author Luchunfang
 */
@Data
public class BatchNotifyDTO {

    @NotBlank(message = "通知类型不能为空")
    private String type;

    @NotEmpty(message = "接收者列表不能为空")
    @Size(max = 1000, message = "单次批量发送不能超过1000个接收者")
    private List<String> receivers;

    @NotBlank(message = "通知内容不能为空")
    @Size(max = 500, message = "通知内容不能超过500字")
    private String content;
}
