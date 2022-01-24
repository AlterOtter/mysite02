package com.poscoict.mysite.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserVo {
	Integer no;
	String name;
	String password;
	String email;
	String gender;
	String join_date;
	

	private UserVo(Builder builder){
        this.no=builder.no;
        this.name=builder.name;
        this.password=builder.password;
        this.email=builder.email;
        this.join_date=builder.join_date;
        this.gender=builder.gender;
  
    }

  
	public static Builder builder(){
        return new Builder();
    } 

    public static class Builder{
    	Integer no;
    	String name;
    	String password;
    	String email;
    	String gender;
    	String join_date;
	    
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
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder gender(String gender){
            this.gender = gender;
            return this;
        }
        public Builder join_date(String join_date){
            this.join_date =join_date;
            return this;
        }
      
        
        public UserVo build(){
            return new UserVo(this);
        }
    }
}
