package washine.washineCore.user;

public class WashineUser implements WashineUserIf {
	private String email;
	private String id;
	
	public WashineUser(String email,String id){
		this.email=email;
		this.id=id;
	}
	@Override
	public String getEmail() {
		if (email!=null) {
			return email;
		} else {
			return null;
		}
	}

	@Override
	public String getId() {
		if (id!=null) {
			return id;
		} else {
			return null;
		}
	}

}
