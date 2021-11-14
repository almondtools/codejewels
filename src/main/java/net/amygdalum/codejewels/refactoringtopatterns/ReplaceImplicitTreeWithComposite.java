package net.amygdalum.codejewels.refactoringtopatterns;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}

	public static class ConstantNode {

		@Override
		public String toString() {
			String content = "<order number='123'>"
							+ "<item number='x1786'>"
							+ "carDoor"
							+ "</item>"
							+ "</order>";
			return "<orders>"
				+ content
				+ "</orders>";
		}
	}
	
	public static class TagNode {
		private String content;

		public TagNode(String content) {
			this.content = content;
		}
		
		@Override
		public String toString() {
			return "<orders>"
				+ content
				+ "</orders>";
		}
	}
}
