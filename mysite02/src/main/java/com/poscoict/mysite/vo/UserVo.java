package com.poscoict.mysite.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVo {
	int no;
	String name;
	String password;
	String email;
	String gender;
	String join_date;
}
