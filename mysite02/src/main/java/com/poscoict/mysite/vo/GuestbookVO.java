package com.poscoict.mysite.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class GuestbookVO {
	private Integer no;
    private String name;
    private String password;
    private String message;
    private String reg_date;
 
}
 