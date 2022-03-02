package com.poscoict.mysite.vo;

import lombok.ToString;

@ToString
public class GuestbookVO {
	private Integer no;
    private String name;
    private String password;
    private String message;
    private String reg_date;
    
    public GuestbookVO() {
		// TODO Auto-generated constructor stub
	}

 
    

	public void setNo(Integer no) {
		this.no = no;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}


	public Integer getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getMessage() {
		return message;
	}

	public String getReg_date() {
		return reg_date;
	}

	private GuestbookVO(Builder builder){
        this.no=builder.no;
        this.name=builder.name;
        this.password=builder.password;
        this.message=builder.message;
        this.reg_date=builder.reg_date;
  
    }

	public static Builder builder(){
        return new Builder();
    } 

    public static class Builder{
    	private Integer no;
        private String name;
        private String password;
        private String message;
        private String reg_date;
	    
        public Builder(){
            this.name="없음";
            this.password="없음";
        }
        
   
     
        public Builder no(Integer no){
            this.no = no;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder password(String password){
            this.password = password;
            return this;
        }
        public Builder message(String message){
            this.message = message;
            return this;
        }
        public Builder reg_date(String reg_date) {
        	this.reg_date =reg_date;
        	return this;
        }
        
        public GuestbookVO build(){
            return new GuestbookVO(this);
        }
    }
}
 