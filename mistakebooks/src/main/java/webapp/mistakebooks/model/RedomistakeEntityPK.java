package webapp.mistakebooks.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RedomistakeEntityPK implements Serializable {
    private String userId;
    private int mistakeId;
    private int times;

    @Column(name = "userID", nullable = false, length = 20)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "mistakeID", nullable = false)
    @Id
    public int getMistakeId() {
        return mistakeId;
    }

    public void setMistakeId(int mistakeId) {
        this.mistakeId = mistakeId;
    }

    @Column(name = "times", nullable = false)
    @Id
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedomistakeEntityPK that = (RedomistakeEntityPK) o;
        return mistakeId == that.mistakeId &&
                times == that.times &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, mistakeId, times);
    }
}
