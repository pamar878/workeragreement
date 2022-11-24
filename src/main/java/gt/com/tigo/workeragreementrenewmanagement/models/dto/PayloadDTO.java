package gt.com.tigo.workeragreementrenewmanagement.models.dto;

import gt.com.tigo.workeragreementrenewmanagement.models.dto.payload.Data;

public class PayloadDTO {
	private Data data;
	private String documentId;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
}