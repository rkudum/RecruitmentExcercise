package ufo.dto.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ufo.dto.UfoSighting;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class UfoSightingMapper implements Function<String, UfoSighting> {
    @Override
    public <V> Function<V, UfoSighting> compose(Function<? super V, ? extends String> before) {
        return null;
    }

    @Override
    public <V> Function<String, V> andThen(Function<? super UfoSighting, ? extends V> after) {
        return null;
    }

    private static final int MIN_NO_OF_TOKENS = 6;
    private static final int DATE_SEEN_INDEX = 0;
    private static final int DATE_REPORTED_INDEX = 1;
    private static final int PLACE_SEEN_INDEX = 2;
    private static final int SHAPE_INDEX = 3;
    private static final int SEEN_DURATION_INDEX = 4;
    private static final int DESCRIPTION_INDEX = 5;
    private static final String FILE_COLUMN_DELIMITER = "\t";
    private final Logger logger = LoggerFactory.getLogger(UfoSightingMapper.class);

    @Override
    public UfoSighting apply(String line) {

        UfoSighting ufoSighting = null;
        String[] lineTokens = line.split(FILE_COLUMN_DELIMITER);

        if (lineTokens.length < MIN_NO_OF_TOKENS) {
            logger.warn(String.format("Invalid data. Check for missing fields or data format for line [%s]", line));
        } else {
            String dateSeen = lineTokens[DATE_SEEN_INDEX].trim();
            String dateReported = lineTokens[DATE_REPORTED_INDEX].trim();
            String placeSeen = lineTokens[PLACE_SEEN_INDEX].trim();
            String shape = lineTokens[SHAPE_INDEX].trim();
            String duration = lineTokens[SEEN_DURATION_INDEX].trim();

            BinaryOperator<String> concat = (a,b)->a+b;
            String description = Arrays.stream(lineTokens).skip(DESCRIPTION_INDEX).reduce("", concat).trim();

            ufoSighting = new UfoSighting(dateSeen, dateReported, placeSeen, shape, duration, description);
        }
        return ufoSighting;
    }
}
