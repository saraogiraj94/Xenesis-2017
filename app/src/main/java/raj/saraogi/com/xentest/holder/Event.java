package raj.saraogi.com.xentest.holder;

public class Event {
    private String eventId, eventCode, departmentId;
    public String eventName, eventDescription, eventPrice, eventDate, eventTime, eventWinPrice, coordinateName, coordinateMobile,url;
    public int imageName,imageIcon;

    public Event(String eventId, String eventCode, String departmentId, String eventName, String eventDescription, String eventPrice, String eventDate, String eventTime, int imageName,String url){
        this.eventId= eventId;
        this.eventCode = eventCode;
        this.departmentId = departmentId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventPrice = eventPrice;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.imageName = imageName;
        this.url=url;
    }

    public String getEventCode() {
        return eventCode;
    }

    public String getEventId() {
        return eventId;
    }

    public String getDepartmentId() {
        return departmentId;
    }
}
