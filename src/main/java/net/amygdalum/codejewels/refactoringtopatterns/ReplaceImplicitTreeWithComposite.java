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
			return new TagNode(content).toString();
		}
	}
	
	public static class TagNode {
		private String content;
		private String name;

		public TagNode(String content) {
			this.content = content;
		}
		
		@Override
		public String toString() {
			name = "orders";
			return "<"
				+ name
				+ ">"
				+ content
				+ "</"
				+ name
				+ ">";
		}
	}
}
