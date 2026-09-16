package com.lcf.codepractice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Luchunfang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchNotifyResultVO {

    /**
     * 总发送数
     */
    private int totalCount;

    /**
     * 成功数
     */
    private int successCount;

    /**
     * 失败数
     */
    private int failCount;

    /**
     * 失败详情列表
     */
    private List<FailDetail> failDetails;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FailDetail {
        private String receiver;
        private String reason;
    }
}
