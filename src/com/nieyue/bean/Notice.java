package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告信息
 * @author yy
 *
 */
public class Notice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公告信息id
	 */
	private Integer noticeId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 *标签类型
	 */
	private String  type;
	
	/**
	 * 内容
	 */
	private String  content;
	/**
	 * 更新日期
	 */
	private Date  updateDate;
	
	
	public Notice() {
		super();
	}


	public Notice(Integer noticeId, String title, String type, String content,
			Date updateDate) {
		super();
		this.noticeId = noticeId;
		this.title = title;
		this.type = type;
		this.content = content;
		this.updateDate = updateDate;
	}


	public Integer getNoticeId() {
		return noticeId;
	}


	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}
