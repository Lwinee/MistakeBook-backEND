package webapp.mistakebooks.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "webapp", catalog = "")
public class UserEntity {
    private String id;
    private String password;
    private String phone;
    private String email;
    private Byte valid;
    private Byte role;

    public UserEntity(){}
    public UserEntity(String id,String password,String phone,String email,Byte valid,Byte role)
    {
        this.id=id;
        this.password=password;
        this.phone=phone;
        this.email=email;
        this.valid=valid;
        this.role=role;
    }
    public UserEntity(String id,String password){
        this.id=id;
        this.password=password;
    }



    @Id
    @Column(name = "ID", nullable = false, length = 20)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 11)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "valid", nullable = true)
    public Byte getValid() {
        return valid;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "role", nullable = true)
    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(password, that.password) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(valid, that.valid) &&
                Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, password, phone, email, valid, role);
    }
}
