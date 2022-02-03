package com.poscoict.mysite.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
	Integer no;
	
	@NotEmpty
	@Length(min =2,max = 10)
	String name;
	String password;
	
//	@Pattern(regexp = " ^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
	@Length(min=4,max=40)
	@NotEmpty
	@Email
	String email;
	String gender;
	String join_date;
	String role;

	public Integer getNo() {
		return no;
	}


	public void setNo(Integer no) {
		this.no = no;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getJoin_date() {
		return join_date;
	}


	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}
	
    public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	private UserVo(Builder builder){
        this.no=builder.no;
        this.name=builder.name;
        this.password=builder.password;
        this.email=builder.email;
        this.join_date=builder.join_date;
        this.gender=builder.gender;
        this.role =builder.role;
  
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
    	String role;
	    
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
        public Builder role(String role){
            this.role =role;
            return this;
        }
      
      
        
        public UserVo build(){
            return new UserVo(this);
        }
    }
}
