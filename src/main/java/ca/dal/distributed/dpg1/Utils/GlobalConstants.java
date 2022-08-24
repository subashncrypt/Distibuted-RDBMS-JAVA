package ca.dal.distributed.dpg1.Utils;

/**
 * @author Bharatwaaj Shankaranarayanan
 * @description Global Constants for Entire Application
 */
public class GlobalConstants {

    /* Global Database & Query Processing Constants */
    public static final String DELIMITER = "|+|";
    public static final String DELIMITER_ESCAPED = "\\|\\+\\|";
    public static final String CURRENT_DB_PATH = "db/";
    public static final String CACHE_DB_PATH = "./cache/";
    public static String DB_PATH = CURRENT_DB_PATH;
    public static final String PRIMARY_KEY_CONSTRAINT = "PK";
    public static final String FOREIGN_KEY_CONSTRAINT = "FK";

    public static final String TABLE_NAME_DELIMITER = "\\.";

    public static final String COLUMN_NAME_DELIMITER = "\\(";
    public static final String COLUMN_NAME_DELIMITER_CLOSE = "\\)";
    public static final String COLUMN_ATTRIBUTES_DELIMITER = "\\|";
    public static final String COLUMN_EMPTY_SPACE = " ";


    public static final String PATTERN_DATABASE_NAME = "[A-Za-z\\d]+";

    public static final String STRING_ERROR_MESSAGE_PREFIX = "ERROR { ";
    public static final String STRING_ERROR_MESSAGE_SUFFIX = " }";

    /* Common String Constants */
    public static final String STRING_UNDERSCORE = "_";
    public static final String STRING_NEXT_LINE = "\n";
    public static final String STRING_COLON_SEPARATOR = " : ";
    public static final String STRING_PIPE_SEPARATOR = " | ";
    public static final String STRING_ARROW_SEPARATOR = " -> ";
    public static final String STRING_HYPHEN = "-";
    public static final String STRING_PERIOD = ".";
    public static final String STRING_AT_THE_RATE = "@";
    public static final String STRING_COMMA = ",";
    public static final String STRING_NULL = null;
    public static final String STRING_EMPTY = "";

    /* Extension Constants */
    public static final String EXTENSION_DOT_TXT = ".txt";

    public static final String ERROR_MESSAGE_INVALID_DB_PATH = "Invalid database path or database does not exist.";
    public static final String EXTENSION_METADATA_FILE = "_metadata.txt";
    public static final String TABLES_PATH = "tables/";
}
