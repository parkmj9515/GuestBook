package guestbook.vo;

import java.util.Date;

public class GuestVo {
	
	private Long no;
    private String name;
    private String password;
    private String content;
    private Date regDate;

    // 기본 생성자
    public GuestVo() {}
    
    public GuestVo(String name, String password, String content) {
    	super();
		this.name = name;
		this.password = password;
		this.content = content;
	}

    // 매개변수가 있는 생성자
    public GuestVo(Long no, String name, String password, String content, Date regDate) {
    	super();
        this.no = no;
        this.name = name;
        this.password = password;
        this.content = content;
        this.regDate = regDate;
    }
    

    // Getter 및 Setter 메서드
    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", name=" + name + ", password=" + password + ", content=" + content + ", regDate="
				+ regDate + "]";
	}


}