package cn.stylefeng.guns.modular.shuheng.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhengpp
 * @since 2020-03-13
 */
@TableName("m_major")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业id
     */
    @TableId(value = "major_id", type = IdType.ID_WORKER)
    private Long majorId;

    /**
     * 学校
     */
    @TableField("school_id")
    private Long schoolId;

    /**
     * 学院id
     */
    @TableField("college_id")
    private Long collegeId;

    /**
     * 专业名称
     */
    @TableField("major_name")
    private String majorName;

    /**
     * 图片
     */
    @TableField("major_img")
    private String majorImg;

    /**
     * 描述
     */
    @TableField("major_desc")
    private String majorDesc;

    private Integer sort;

    @TableField("del_flag")
    private String delFlag;

    @TableField("create_user")
    private Long createUser;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_user")
    private Long updateUser;

    @TableField("update_time")
    private Date updateTime;

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }
    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
    public String getMajorImg() {
        return majorImg;
    }

    public void setMajorImg(String majorImg) {
        this.majorImg = majorImg;
    }
    public String getMajorDesc() {
        return majorDesc;
    }

    public void setMajorDesc(String majorDesc) {
        this.majorDesc = majorDesc;
    }
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Major{" +
        "majorId=" + majorId +
        ", schoolId=" + schoolId +
        ", collegeId=" + collegeId +
        ", majorName=" + majorName +
        ", majorImg=" + majorImg +
        ", majorDesc=" + majorDesc +
        ", sort=" + sort +
        ", delFlag=" + delFlag +
        ", createUser=" + createUser +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}";
    }
}
