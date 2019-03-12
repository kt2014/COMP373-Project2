package model.facilityMaintenance;

public class MaintCost {
	private int costID;
	private float laborCost;
	private float materialCost;
	private float extra;
	
	public int getCostID() {
		return costID;
	}
	public void setCostID(int costID) {
		this.costID = costID;
	}
	public float getLaborCost() {
		return laborCost;
	}
	public void setLaborCost(float laborCost) {
		this.laborCost = laborCost;
	}
	public float getMaterialCost() {
		return materialCost;
	}
	public void setMaterialCost(float materialCost) {
		this.materialCost = materialCost;
	}
	public float getExtra() {
		return extra;
	}
	public void setExtra(float extra) {
		this.extra = extra;
	}
}
