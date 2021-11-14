package net.amygdalum.codejewels.refactoringtopatterns;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ReplaceImplicitTreeWithComposite {

	public String serializeOrders() {
		Node item = new TagNode("item")
			.addAttribute("number", "x1786")
			.addNode(new TextNode("carDoor"));
		Node order = new TagNode("order")
			.addAttribute("number", "123")
			.addNode(item);
		return new TagNode("orders")
			.addNode(order)
			.toString();
	}

	public interface Node {

	}

	public static class TextNode implements Node {
		private String text;

		public TextNode(String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	public static class TagNode implements Node {
		private String name;
		private List<Node> nodes;
		private SortedMap<String, String> attributes;

		public TagNode(String name) {
			this.name = name;
			this.attributes = new TreeMap<>();
			this.nodes = new ArrayList<>();
		}

		public TagNode addAttribute(String name, String value) {
			this.attributes.put(name, value);
			return this;
		}

		public TagNode addNode(Node node) {
			this.nodes.add(node);
			return this;
		}

		@Override
		public String toString() {
			return "<" + name + attributes.entrySet().stream()
				.map(e -> " " + e.getKey() + "='" + e.getValue() + "'")
				.collect(joining()) + ">"
				+ nodes.stream().map(Object::toString).collect(joining())
				+ "</" + name + ">";
		}
	}
}
