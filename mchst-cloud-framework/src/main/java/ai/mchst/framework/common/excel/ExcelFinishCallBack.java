package ai.mchst.framework.common.excel;

import java.util.List;

/**
 * excel读取数据完成
 */
public interface ExcelFinishCallBack<T> {

    /**
     * 导出后置处理数据
     * Do after all analysed.
     *
     * @param result the result
     */
    void doAfterAllAnalysed(List<T> result);

    /**
     * Do save batch.
     *
     * @param result the result
     */
    default void doSaveBatch(List<T> result) {
    }
}

