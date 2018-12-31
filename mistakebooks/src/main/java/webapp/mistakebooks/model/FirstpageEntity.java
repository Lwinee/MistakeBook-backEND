package webapp.mistakebooks.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "firstpage", schema = "webapp", catalog = "")
public class FirstpageEntity {
    private int mistakeId;
    private String mistakeTitle;
    private String mistakeCause;
    private String userId;
    private String subject;
    private String tag;
    public FirstpageEntity(){}
    public FirstpageEntity(int mistakeId,String mistakeTitle,String mistakeCause,String userId, String subject,String tag) {
        this.mistakeId = mistakeId;
        this.mistakeTitle = mistakeTitle;
        this.userId=userId;
        this.mistakeCause = mistakeCause;
        this.subject=subject;
        this.tag=tag;
    }
    @Id
    @Column(name = "mistakeID", nullable = false)
    public int getMistakeId() {
        return mistakeId;
    }

    public void setMistakeId(int mistakeId) {
        this.mistakeId = mistakeId;
    }

    @Basic
    @Column(name = "mistakeTitle", nullable = true, length = 200)
    public String getMistakeTitle() {
        return mistakeTitle;
    }

    public void setMistakeTitle(String mistakeTitle) {
        this.mistakeTitle = mistakeTitle;
    }

    @Basic
    @Column(name = "mistakeCause", nullable = true, length = 200)
    public String getMistakeCause() {
        return mistakeCause;
    }

    public void setMistakeCause(String mistakeCause) {
        this.mistakeCause = mistakeCause;
    }

    @Basic
    @Column(name = "userID", nullable = true, length = 20)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "subject", nullable = true, length = 20)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = 50)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstpageEntity that = (FirstpageEntity) o;
        return mistakeId == that.mistakeId &&
                Objects.equals(mistakeTitle, that.mistakeTitle) &&
                Objects.equals(mistakeCause, that.mistakeCause) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mistakeId, mistakeTitle, mistakeCause, userId, subject, tag);
    }
}
