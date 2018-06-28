package com.sojson.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author xj
 * @version 1.0,2017年8月7日
 * 
 */
public class UUser implements Serializable {
	private static final long serialVersionUID = 1L;
	// 0:禁止登录
	public static final Long _0 = new Long(0);
	// 1:有效
	public static final Long _1 = new Long(1);
	private Long id;
	/** 昵称 */
	private String nickname;
	/** 邮箱 | 登录帐号 */
	private String email;
	/** 密码 */
	private transient String pswd;
	/** 创建时间 */
	private Date createTime;
	/** 最后登录时间 */
	private Date lastLoginTime;
	/** 1:有效，0:禁止登录 */
	private Long status;
	/**
	 * 用户
	 * 
	 * @author zjf
	 * @version 1.0,2017年8月31日
	 * 
	 */
	private int sex;//0:男 1：女
	private String phone;//电话
	private String position;//职位
	private String u_type;//使用类型
	private String hiredate;//入职时间
	private String leavedate;//离职时间
	private String station;//检测站
	private int work_status;//工作状态0 正常 1 离职
	private int roleid;//角色id
	public int getWork_status() {
		return work_status;
	}

	public void setWork_status(int work_status) {
		this.work_status = work_status;
	}

	public UUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getU_type() {
		return u_type;
	}

	public void setU_type(String u_type) {
		this.u_type = u_type;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public String getLeavedate() {
		return leavedate;
	}

	public void setLeavedate(String leavedate) {
		this.leavedate = leavedate;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	@Override
	public String toString() {
		return "UUser [id=" + id + ", nickname=" + nickname + ", email="
				+ email + ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + ", status=" + status + ", sex=" + sex
				+ ", phone=" + phone + ", position=" + position + ", u_type="
				+ u_type + ", hiredate=" + hiredate + ", leavedate="
				+ leavedate + ", station=" + station + ", work_status="
				+ work_status + ", roleid=" + roleid + "]";
	}
	
	
	
}