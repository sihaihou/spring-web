package com.reyco.kn.core.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 订单实体
 * @author Admin
 *
 */
public class OrderEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2274028801424663664L;
	private Integer id;
	private String no;
	private String content;
	private Integer state;
	private String desc;
	private Date gmtExpire;
	private Date gmtCreate;

	public OrderEntity() {
	}
	private OrderEntity(OrderEnitiyBuilder builder) {
		this.id = builder.id;
		this.no = builder.no;
		this.content = builder.content;
		this.state = builder.state;
		this.desc = builder.desc;
		this.gmtExpire = builder.gmtExpire;
		this.gmtCreate = builder.gmtCreate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getGmtExpire() {
		return gmtExpire;
	}

	public void setGmtExpire(Date gmtExpire) {
		this.gmtExpire = gmtExpire;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public static class OrderEnitiyBuilder {
		private Integer id;
		private String no;
		private String content;
		private Integer state;
		private String desc;
		private Date gmtExpire;
		private Date gmtCreate;
		
		public OrderEnitiyBuilder builderId(Integer id) {
			this.id = id;
			return this;
		}
		public OrderEnitiyBuilder builderNo(String no) {
			this.no = no;
			return this;
		}
		public OrderEnitiyBuilder builderContent(String content) {
			this.content = content;
			return this;
		}
		public OrderEnitiyBuilder builderState(Integer state) {
			this.state = state;
			return this;
		}
		public OrderEnitiyBuilder builderGmtDesc(String desc) {
			this.desc = desc;
			return this;
		}
		public OrderEnitiyBuilder builderGmtExpire(Date gmtExpire) {
			this.gmtExpire = gmtExpire;
			return this;
		}
		public OrderEnitiyBuilder builderGmtCreate(Date gmtCreate) {
			this.gmtCreate = gmtCreate;
			return this;
		}
		
		public OrderEntity builder() {
			return new OrderEntity(this);
		}
	}

	@Override
	public String toString() {
		return "OrderEntity [id=" + id + ", no=" + no + ", content=" + content + ", state=" + state + ", desc=" + desc
				+ ", gmtExpire=" + gmtExpire + ", gmtCreate=" + gmtCreate + "]";
	}
}
