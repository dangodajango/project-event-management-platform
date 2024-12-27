package self.project.eventmanagement.util;

import com.jayway.jsonpath.JsonPath;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimestampResultMatcher implements ResultMatcher {

    private static final Duration ALLOWED_TIMESTAMP_DIFFERENCE = Duration.ofSeconds(1);

    @Override
    public void match(MvcResult result) throws Exception {
        String timestampString = JsonPath.parse(result.getResponse().getContentAsString()).read("$.timestamp");
        LocalDateTime timestamp = LocalDateTime.parse(timestampString);
        LocalDateTime currentTimestamp = LocalDateTime.now();
        Duration actualDifferenceBetweenTimestamps = Duration.between(currentTimestamp, timestamp).abs();
        if (isDurationDifferenceBiggerThanAllowedDifference(actualDifferenceBetweenTimestamps)) {
            throw new AssertionError("The timestamp difference %s was bigger than the allowed difference %s".formatted(actualDifferenceBetweenTimestamps, ALLOWED_TIMESTAMP_DIFFERENCE));
        }
    }

    private boolean isDurationDifferenceBiggerThanAllowedDifference(Duration actualDifference) {
        return actualDifference.compareTo(ALLOWED_TIMESTAMP_DIFFERENCE) > 0;
    }
}
