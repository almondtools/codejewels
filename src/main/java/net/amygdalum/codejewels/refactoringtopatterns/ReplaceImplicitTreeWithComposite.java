package net.amygdalum.codejewels.refactoringtopatterns;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}

	public static class ConstantNode {

		@Override
		public String toString() {
			return "<orders>"
				+ "<order number='123'>"
				+ "<item number='x1786'>"
				+ "carDoor"
				+ "</item>"
				+ "</order>"
				+ "</orders>";
		}
	}
}
