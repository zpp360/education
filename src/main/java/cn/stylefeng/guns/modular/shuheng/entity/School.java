package cn.stylefeng.guns.modular.shuheng.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengpp
 * @since 2019-10-22
 */
@TableName("m_school")
public class School implements Serializable {

    private static final long serialVersionUID = 2528059331293603511L;
    /**
     * ID
     */
    @TableId(value = "school_id", type = IdType.ID_WORKER)
    private Long schoolId;

    /**
     * 学校名称
     */
    @TableField("school_name")
    private String schoolName;

    /**
     * 电话
     */
    @TableField("school_tel")
    private String schoolTel;

    /**
     * 地址
     */
    @TableField("school_addres")
    private String schoolAddres;

    /**
     * 学年
     */
    @TableField(value = "school_year")
    private Integer schoolYear;

    /**
     * 学校简介
     */
    @TableField("school_desc")
    private String schoolDesc;

    /**
     * 场馆图片
     */
    @TableField("school_img")
    private String schoolImg;


    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private Long updateUser;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolTel() {
        return schoolTel;
    }

    public void setSchoolTel(String schoolTel) {
        this.schoolTel = schoolTel;
    }

    public String getSchoolAddres() {
        return schoolAddres;
    }

    public void setSchoolAddres(String schoolAddres) {
        this.schoolAddres = schoolAddres;
    }

    public String getSchoolDesc() {
        return schoolDesc;
    }

    public void setSchoolDesc(String schoolDesc) {
        this.schoolDesc = schoolDesc;
    }

    public String getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    @Override
    public String toString() {
        return "School{" +
                "schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", schoolTel='" + schoolTel + '\'' +
                ", schoolAddres='" + schoolAddres + '\'' +
                ", schoolDesc='" + schoolDesc + '\'' +
                ", schoolImg='" + schoolImg + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                '}';
    }
}
