package gt.com.tigo.workeragreementrenewmanagement.models.dto.payload;

import java.util.ArrayList;

public class AgreementGroups {
	private String groupName;
	private ArrayList<AgreementItem> agreementItem = new ArrayList<AgreementItem>();

	// Getter Methods

	public String getGroupName() {
		return groupName;
	}

	// Setter Methods

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public ArrayList<AgreementItem> getAgreementItem() {
		return agreementItem;
	}

	public void setAgreementItem(ArrayList<AgreementItem> agreementItem) {
		this.agreementItem = agreementItem;
	}
	
}
