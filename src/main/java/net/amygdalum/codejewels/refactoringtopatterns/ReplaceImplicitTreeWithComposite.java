package net.amygdalum.codejewels.refactoringtopatterns;

import static java.util.stream.Collectors.joining;

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
			String content = new TagNode("order", item)
				.addAttribute("number", "123")
				.toString();
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

		public TagNode addAttribute(String name, String value) {
			this.attributes.put(name, value);
			return this;
		}

		@Override
		public String toString() {
			return "<" + name + attributes.entrySet().stream()
				.map(e -> " " + e.getKey() + "='" + e.getValue() + "'")
				.collect(joining()) + ">"
				+ content
				+ "</" + name + ">";
		}
	}
}
