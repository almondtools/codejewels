package net.amygdalum.codejewels.refactoringtopatterns;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}
	
	public static class ConstantNode {
		
		@Override
		public String toString() {
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
}
