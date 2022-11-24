package gt.com.tigo.workeragreementrenewmanagement.models.dto.payload;

public class Attributes {
	private String id;
	private String idType;
	private String type;

	// Getter Methods

	public String getId() {
		return id;
	}

	public String getIdType() {
		return idType;
	}

	public String getType() {
		return type;
	}

	// Setter Methods

	public void setId(String id) {
		this.id = id;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public void setType(String type) {
		this.type = type;
	}
}