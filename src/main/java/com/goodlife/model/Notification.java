package com.goodlife.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION", catalog = "goodlife")
public class Notification implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notif_id", nullable = true, unique = true, columnDefinition = "MEDIUMINT AUTO_INCREMENT")
	private Integer notificationId;

	@JoinColumn(name = "from_usrid", referencedColumnName="usr_id", nullable = false)
	private Integer fromUserId;
	
	@JoinColumn(name = "to_usrid", referencedColumnName="usr_id", nullable = false)
	private Integer toUserId;
	
	// visibility from notification list. default true
	// alternative is to have an expiry date. TIMESTAMP.
	@Column(name = "visibility", columnDefinition = "TINYINT(1) DEFAULT 1")
	private Boolean visibility;

	@Column(name = "notif_txt") 
	private String notificationText;
	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notifId) {
		this.notificationId = notifId;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public String getNotificationText() {
		return notificationText;
	}

	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	
	
}
