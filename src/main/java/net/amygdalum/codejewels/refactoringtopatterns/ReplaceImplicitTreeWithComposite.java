package net.amygdalum.codejewels.refactoringtopatterns;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}
	
	public static class ConstantNode {
		
		@Override
		public String toString() {
			String orders1 = "<orders>";
			String orders2 = orders1 + "<order number='123'>";
			String orders3 = orders2 + "<item number='x1786'>";
			String orders4 = orders3 + "carDoor";
			String orders5 = orders4 + "</item>";
			String orders6 = orders5 + "</order>";
			String orders7 = orders6 + "</orders>";
			return orders7;
		}
	}
}
