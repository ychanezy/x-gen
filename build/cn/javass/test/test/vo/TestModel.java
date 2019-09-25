package cn.javass.test.test.vo;

public class TestModel implements java.io.Serializable{
	public static final String TBL_NAME = "tbl_Test";
	private String uuid;
	private String userId;
	private String pwd;
	private String name;
	private String wokerNum;
	private String sex;
	private String tel;
	private String qq;
	private String email;
	private String address;
	private String fromUni;
	private long greaduateTime;
	private String speciality;
	private long Birthday;
	private String UserType;
	private String UserDocUuid;
	private int delFlag;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TestModel other = (TestModel) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}	
	public String toString(){
		return buffer.toString(); 
	}
}
