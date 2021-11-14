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
		private String name;
		private String content;

		public TagNode(String content) {
			this.content = content;
			this.name = "orders";
		}
		
		@Override
		public String toString() {
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
