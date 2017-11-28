package pojo;

import javax.persistence.*;

@Entity
@Table(name = "version", schema = "kson", catalog = "")
public class VersionEntity {
    private int vId;
    private String versionName;
    private String versionCode;
    private String apkUrl;
    private int type;

    @Id
    @Column(name = "v_id")
    public int getvId() {
        return vId;
    }

    public void setvId(int vId) {
        this.vId = vId;
    }

    @Basic
    @Column(name = "versionName")
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    @Basic
    @Column(name = "versionCode")
    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    @Basic
    @Column(name = "apk_url")
    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionEntity that = (VersionEntity) o;

        if (vId != that.vId) return false;
        if (type != that.type) return false;
        if (versionName != null ? !versionName.equals(that.versionName) : that.versionName != null) return false;
        if (versionCode != null ? !versionCode.equals(that.versionCode) : that.versionCode != null) return false;
        if (apkUrl != null ? !apkUrl.equals(that.apkUrl) : that.apkUrl != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vId;
        result = 31 * result + (versionName != null ? versionName.hashCode() : 0);
        result = 31 * result + (versionCode != null ? versionCode.hashCode() : 0);
        result = 31 * result + (apkUrl != null ? apkUrl.hashCode() : 0);
        result = 31 * result + type;
        return result;
    }
}
