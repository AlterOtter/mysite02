package com.poscoict.mysite.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserVo {
	String name;
	String password;
	String email;
	String gender;
	String join_date;
}
