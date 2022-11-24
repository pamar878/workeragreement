package gt.com.tigo.workeragreementrenewmanagement.models.dto.payload;

import java.util.ArrayList;

public class Data {
	private String type;
	private String source;
	private AttributesObject attributes;
	private ArrayList<AgreementItem> agreementItem;
	private ArrayList<AgreementGroups> agreementGroups;
	private EngagedParty engagedParty;

	// Getter Methods

	public String getType() {
		return type;
	}

	public String getSource() {
		return source;
	}

	// Setter Methods

	public void setType(String type) {
		this.type = type;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public ArrayList<AgreementItem> getAgreementItem() {
		return agreementItem;
	}

	public void setAgreementItem(ArrayList<AgreementItem> agreementItem) {
		this.agreementItem = agreementItem;
	}

	public EngagedParty getEngagedParty() {
		return engagedParty;
	}

	public void setEngagedParty(EngagedParty engagedParty) {
		this.engagedParty = engagedParty;
	}

	public ArrayList<AgreementGroups> getAgreementGroups() {
		return agreementGroups;
	}

	public void setAgreementGroups(ArrayList<AgreementGroups> agreementGroups) {
		this.agreementGroups = agreementGroups;
	}

	public AttributesObject getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributesObject attributes) {
		this.attributes = attributes;
	}
	
}