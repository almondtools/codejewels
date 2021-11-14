package net.amygdalum.codejewels.refactoringtopatterns;

import java.util.SortedMap;
import java.util.TreeMap;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}

	public static class ConstantNode {

		@Override
		public String toString() {
			String item = "<item number='x1786'>"
							+ "carDoor"
							+ "</item>";
			String content = "<order number='123'>"
				+ item
				+ "</order>";
			return new TagNode("orders", content).toString();
		}
	}

	public static class TagNode {
		private String name;
		private String content;
		private SortedMap<String, String> attributes;

		public TagNode(String name, String content) {
			this.name = name;
			this.content = content;
			this.attributes = new TreeMap<>();
		}

		@Override
		public String toString() {
			return "<" + name + ">"
				+ content
				+ "</" + name + ">";
		}
	}
}
