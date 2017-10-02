package bean;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "car", schema = "kson", catalog = "")
public class CarEntity {
    private long carid;
    private long sellerid;
    private Timestamp createtime;
    private long uid;
    private long pid;
    private Integer num;

    @Id
    @Column(name = "carid")
    public long getCarid() {
        return carid;
    }

    public void setCarid(long carid) {
        this.carid = carid;
    }

    @Basic
    @Column(name = "sellerid")
    public long getSellerid() {
        return sellerid;
    }

    public void setSellerid(long sellerid) {
        this.sellerid = sellerid;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "uid")
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "pid")
    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarEntity carEntity = (CarEntity) o;

        if (carid != carEntity.carid) return false;
        if (sellerid != carEntity.sellerid) return false;
        if (uid != carEntity.uid) return false;
        if (pid != carEntity.pid) return false;
        if (createtime != null ? !createtime.equals(carEntity.createtime) : carEntity.createtime != null) return false;
        if (num != null ? !num.equals(carEntity.num) : carEntity.num != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (carid ^ (carid >>> 32));
        result = 31 * result + (int) (sellerid ^ (sellerid >>> 32));
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (int) (uid ^ (uid >>> 32));
        result = 31 * result + (int) (pid ^ (pid >>> 32));
        result = 31 * result + (num != null ? num.hashCode() : 0);
        return result;
    }
}
