package mbeans;

import javax.management.*;

public class PointsCounter extends NotificationBroadcasterSupport implements PointsCounterMBean {
    int totalPoints = 0;
    static int sequenceNumber = 1;

    @Override
    public int getTotalPointsCount() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public void incrementPointsCount() {
        totalPoints++;
        if ( totalPoints % 15 == 0) {
            Notification n = new AttributeChangeNotification(this,
                    sequenceNumber++, System.currentTimeMillis(),
                    "The number of points is a multiple of 15", "Multiplicity", "int",
                    totalPoints - 1, totalPoints);
            sendNotification(n);
        }
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
