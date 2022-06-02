package mbeans;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointsInfo extends NotificationBroadcasterSupport implements PointsInfoMBean {
    public int totalPoints = 0;
    public int hits = 0;
    public int badResults = 0;
    private long sequenceNumber = 1;

    public void setTotalPoints(int totalPoints) {
        System.out.println(totalPoints);
        this.totalPoints = totalPoints;
    }

    public void incrementBadResult(){
        badResults++;
        if (badResults % 3 == 0){
            Notification n = new AttributeChangeNotification(this,
                    sequenceNumber++, System.currentTimeMillis(),
                    "3 bad results in a row", "Bad results", "int",
                    sequenceNumber - 1, this.badResults);

            sendNotification(n);
        }
    }

    public void setBadResult(int badResults){
        this.badResults = badResults;
    }

    public void setHits(int hits) {
        System.out.println(hits);
        this.hits = hits;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public int getHits() {
        return hits;
    }

    @Override
    public int getBadResult() {
        return badResults;
    }


    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }
}
