package cn.codemao.research.refactor.common.util;

import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author switch
 * @since 2020/11/12
 */
public final class ZonedDateTimeUtil {
    private ZonedDateTimeUtil() {
        throw new UnsupportedOperationException();
    }

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("Asia/Shanghai");

    public static ZonedDateTime now() {
        return now(DEFAULT_ZONE_ID);
    }

    public static ZonedDateTime now(@NotNull ZoneId zoneId) {
        return ZonedDateTime.now(zoneId);
    }
}
