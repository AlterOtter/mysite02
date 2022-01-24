package com.poscoict.mysite.vo;

import com.poscoict.mysite.vo.UserVo.Builder;

import lombok.Data;
import lombok.ToString;

@ToString
public class BoardVo {
	private Integer no=0;
	private String title;
	private String contents;
	private Integer hit;
	private Integer groupNo;
	private Integer orderNo;
	private Integer depth;
	private String regDate;
	private UserVo userVo;
	private String userName;
	
	public BoardVo() {
		// TODO Auto-generated constructor stub
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Integer getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	private BoardVo(Builder builder){
        this.no=builder.no;
        this.title=builder.title;
        this.contents=builder.contents;
        this.hit=builder.hit;
        this.groupNo=builder.groupNo;
        this.orderNo=builder.orderNo;
        this.depth = builder.depth;
        this.regDate = builder.regDate;
        this.userVo = builder.userVo;
        this.userName = builder.userName;
    }

  
	public static Builder builder(){
        return new Builder();
    } 

    public static class Builder{
    	private Integer no;
    	private String title;
    	private String contents;
    	private Integer hit;
    	private Integer groupNo;
    	private Integer orderNo;
    	private Integer depth;
    	private String regDate;
    	private UserVo userVo;
    	private String userName;
	    
        public Builder(){
            this.title="없음";
            this.contents="없음";
        }
        
        public Builder no(Integer no){
            this.no = no;
            return this;
        }
        
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder contents(String contents){
            this.contents = contents;
            return this;
        }
        public Builder hit(Integer hit){
            this.hit = hit;
            return this;
        }
        public Builder groupNo(Integer groupNo){
            this.groupNo = groupNo;
            return this;
        }
        public Builder orderNo(Integer orderNo){
            this.orderNo =orderNo;
            return this;
        }
        public Builder depth(Integer depth){
            this.depth = depth;
            return this;
        }
        public Builder regDate(String regDate){
            this.regDate =regDate;
            return this;
        }
      
        public Builder userVo(UserVo userVo){
            this.userVo = userVo;
            return this;
        }
        public Builder userName(String userName){
            this.userName =userName;
            return this;
        }
      
      
        
        public BoardVo build(){
            return new BoardVo(this);
        }
    }
}
