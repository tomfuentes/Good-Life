package com.goodlife.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS", catalog = "goodlife")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usr_id", unique = true, nullable = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer userId;
	
	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;
	
	@Column(name = "usr_nm", unique = true, nullable = true, length = 50)
	private String username;

	@Column(name = "pwd", nullable = false, length = 200)
	private String password;

	@Column(name = "role_typ_cd", nullable = false, length = 1)
	private char roleTypeCode;
	
	@Column(name = "rgstrd", columnDefinition = "TINYINT", nullable = false, length = 1)
	private boolean registered;
	
	@Column(name = "invit_cd", nullable = false)
	private Integer invitationCode;
	
	@Column(name = "invit_by", nullable = true, length = 50)
	private String invitedBy;
	
	@Column(name = "invit_dt", nullable = true)
	private Date invitedDate;
	
    @Column(name="frst_nm", length = 50)
    private String firstname;
 
    @Column(name="lst_nm", length = 50)
    private String lastname;
    
    @Column(name="city", length = 50)
    private String city;
 
    @Column(name="state", length = 2)
    private String state;
    
    @Column(name="abt_me", length = 250)
    private String aboutMe;
    
    @Column(name="prf_img_path") 
    private String profileImagePath;
	
	@Column(name = "promo_dt")
	private Date promotionDate;
	
	@Column(name = "users_ts", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP()")
	private Date usersTS;
	
    public Users() {
    	
    }
    
	public Users(Integer userId, String email, String username,
			String password, char roleTypeCode, boolean registered,
			Integer invitationCode, String invitedBy, Date invitedDate,
			Integer userStatusId, String firstname, String lastname,
			String city, String state, String aboutMe, String profileImagePath,
			Date promotionDate, Date usersTS) {
		super();
		this.userId = userId;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleTypeCode = roleTypeCode;
		this.registered = registered;
		this.invitationCode = invitationCode;
		this.invitedBy = invitedBy;
		this.invitedDate = invitedDate;
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.state = state;
		this.aboutMe = aboutMe;
		this.profileImagePath = profileImagePath;
		this.promotionDate = promotionDate;
		this.usersTS = usersTS;
	}

	

	public Date getUsersTS() {
		return usersTS;
	}

	public void setUsersTS(Date usersTS) {
		this.usersTS = usersTS;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public Integer getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(Integer invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getInvitedBy() {
		return invitedBy;
	}

	public void setInvitedBy(String invitedBy) {
		this.invitedBy = invitedBy;
	}

	public Date getInvitedDate() {
		return invitedDate;
	}

	public void setInvitedDate(Date invitedDate) {
		this.invitedDate = invitedDate;
	}
	
    public String getFirstname() {
        return firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public char getRoleTypeCode() {
		return roleTypeCode;
	}

	public void setRoleTypeCode(char roleTypeCode) {
		this.roleTypeCode = roleTypeCode;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public Date getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}
}
