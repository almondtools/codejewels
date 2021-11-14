package net.amygdalum.codejewels.refactoringtopatterns;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		String orders = "<orders>";
		orders += "<order number='123'>";
		orders += "<item number='x1786'>";
		orders += "carDoor";
		orders += "</item>";
		orders += "</order>";
		orders += "</orders>";
		return orders;
	}
}
