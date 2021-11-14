package net.amygdalum.codejewels.refactoringtopatterns;

import static java.util.stream.Collectors.joining;

import java.util.SortedMap;
import java.util.TreeMap;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		return new ConstantNode().toString();
	}

	public interface Node {

	}

	public static class TextNode implements Node {
		@Override
		public String toString() {
			return super.toString();
		}
	}

	public static class ConstantNode implements Node {

		@Override
		public String toString() {
			Node item = new TagNode("item")
				.addAttribute("number", "x1786")
				.addContent("carDoor");
			Node order = new TagNode("order")
				.addAttribute("number", "123")
				.addNode(item);
			return new TagNode("orders")
				.addNode(order)
				.toString();
		}
	}

	public static class TagNode implements Node {
		private String name;
		private String content;
		private SortedMap<String, String> attributes;

		public TagNode(String name) {
			this.name = name;
			this.attributes = new TreeMap<>();
		}

		public TagNode addAttribute(String name, String value) {
			this.attributes.put(name, value);
			return this;
		}

		public TagNode addNode(Node node) {
			this.content = node.toString();
			return this;
		}

		public TagNode addContent(String content) {
			this.content = content;
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
