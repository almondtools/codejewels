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
			return new TagNode("orders", content).toString();
		}
	}

	public static class TagNode {
		private String name;
		private String content;

		public TagNode(String name, String content) {
			this.name = name;
			this.content = content;
		}

		@Override
		public String toString() {
			return "<" + name + ">"
				+ content
				+ "</" + name + ">";
		}
	}
}
