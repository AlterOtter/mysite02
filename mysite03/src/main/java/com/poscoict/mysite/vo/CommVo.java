package com.poscoict.mysite.vo;

import com.poscoict.mysite.vo.CommVo.Builder;

public class CommVo {
	private Integer comm_sn;
 	private Integer comm_mem_sn;
    private Integer comm_bd_sn;
    private String comm_content;
    private String comm_date;
    private String mem_nm;

    public CommVo() {
		// TODO Auto-generated constructor stub
	}
    
	public String getMem_nm() {
		return mem_nm;
	}


	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}


	public Integer getComm_sn() {
		return comm_sn;
	}


	public void setComm_sn(Integer comm_sn) {
		this.comm_sn = comm_sn;
	}


	public Integer getComm_mem_sn() {
		return comm_mem_sn;
	}


	public void setComm_mem_sn(Integer comm_mem_sn) {
		this.comm_mem_sn = comm_mem_sn;
	}


	public Integer getComm_bd_sn() {
		return comm_bd_sn;
	}


	public void setComm_bd_sn(Integer comm_bd_sn) {
		this.comm_bd_sn = comm_bd_sn;
	}


	public String getComm_content() {
		return comm_content;
	}


	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}


	public String getComm_date() {
		return comm_date;
	}


	public void setComm_date(String comm_date) {
		this.comm_date = comm_date;
	}


	private CommVo(Builder builder){
        this.comm_sn=builder.comm_sn;
        this.comm_mem_sn=builder.comm_mem_sn;
        this.comm_bd_sn=builder.comm_bd_sn;
        this.comm_content=builder.comm_content;
        this.comm_date=builder.comm_date;
        this.mem_nm=builder.mem_nm;
    }

    
    public static Builder builder(){
        return new Builder();
    } 

    public static class Builder{
    	private Integer comm_sn;
     	private Integer comm_mem_sn;
        private Integer comm_bd_sn;
        private String comm_content;
        private String comm_date;
        private String mem_nm;
        
	    
        public Builder(){
        	comm_content="댓글좀 입력해라";
        }
        
        public Builder comm_sn(Integer comm_sn){
            this.comm_sn = comm_sn;
            return this;
        }
        public Builder comm_mem_sn(Integer comm_mem_sn){
            this.comm_mem_sn = comm_mem_sn;
            return this;
        }
        public Builder comm_bd_sn(Integer comm_bd_sn){
            this.comm_bd_sn = comm_bd_sn;
            return this;
        }
        public Builder comm_content(String comm_content){
            this.comm_content = comm_content;
            return this;
        }
        public Builder comm_date(String comm_date){
            this.comm_date = comm_date;
            return this;
        }
        public Builder mem_nm(String mem_nm){
            this.mem_nm = mem_nm;
            return this;
        }
       
        public CommVo build(){
            return new CommVo(this);
        }
    }
}
