package mbeans;

public class HitProbability implements HitProbabilityMBean{
    public int totalPoints = 0;
    public int hits = 0;

    public void setTotalPoints(int totalPoints) {
        System.out.println(totalPoints);
        this.totalPoints = totalPoints;
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
    public String hitProbability() {
        return (int)((double) hits/ (double) totalPoints *100) + "%";
    }
}
