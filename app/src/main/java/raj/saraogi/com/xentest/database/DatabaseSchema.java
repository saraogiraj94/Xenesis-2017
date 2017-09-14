package raj.saraogi.com.xentest.database;

import android.provider.BaseColumns;

public class DatabaseSchema {
    public static abstract class Events implements BaseColumns {
        public static final String TABLE_NEAME = "events";
        public static final String EVENT_ID = "event_id";
        public static final String EVENT_CODE = "event_code";
        public static final String DEPARTMENT_ID = "dept_id";
        public static final String EVENT_NAME ="event_name";
        public static final String EVENT_DESCRIPTION="event_desc";
        public static final String EVENT_PRICE="event_price";
        public static final String EVENT_DATE = "event_date";
        public static final String EVENT_TIME = "event_time";
        public static final String IMAGE_NAME="image_name";
        public static final String URL="url";
        public static final String IMAGE_ICON="image_icon";
    }
}
