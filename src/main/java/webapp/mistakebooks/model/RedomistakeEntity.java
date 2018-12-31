package webapp.mistakebooks.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "redomistake", schema = "webapp", catalog = "")
@IdClass(RedomistakeEntityPK.class)
public class RedomistakeEntity implements Serializable {
    private String userId;
    private int mistakeId;
    private int times;
    private String answer;
    private Timestamp redoDate;
    public RedomistakeEntity() {
    }
    public RedomistakeEntity(String userId,int mistakeId,String answer) {
        this.userId = userId;
        this.mistakeId = mistakeId;
        this.answer = answer;
    }
    @Id
    @Column(name = "userID", nullable = false, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "mistakeID", nullable = false)
    public int getMistakeId() {
        return mistakeId;
    }

    public void setMistakeId(int mistakeId) {
        this.mistakeId = mistakeId;
    }

    @Id
    @Column(name = "times", nullable = false)
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Basic
    @Column(name = "answer", nullable = true, length = 100)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "redoDate", nullable = false)
    public Timestamp getRedoDate() {
        return redoDate;
    }

    public void setRedoDate(Timestamp redoDate) {
        this.redoDate = redoDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedomistakeEntity that = (RedomistakeEntity) o;
        return mistakeId == that.mistakeId &&
                times == that.times &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(redoDate, that.redoDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, mistakeId, times, answer, redoDate);
    }
}
