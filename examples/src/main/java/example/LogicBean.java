package example;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * ロジックを出力するためのモデル.
 *
 * @author takashno
 */
@Data
@Builder
public class LogicBean implements Serializable {

    /**
     * デフォルトシリアルバージョンUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 対象.
     */
    private String target;
}
