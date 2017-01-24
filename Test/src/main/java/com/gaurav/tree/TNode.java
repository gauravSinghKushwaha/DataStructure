package com.gaurav.tree;

import java.util.ArrayList;
import java.util.List;

public final class TNode<E> {
	private final E value;
	private TNode<E> left;
	private TNode<E> right;

	public TNode(final E value, final TNode<E> left, final TNode<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public E value() {
		return value;
	}

	public TNode<E> left() {
		return left;
	}

	public TNode<E> right() {
		return right;
	}

	public void setLeft(final TNode<E> left) {
		this.left = left;
	}

	public void setRight(final TNode<E> right) {
		this.right = right;
	}

	@Override
	public String toString() {
		final StringBuilder strB = new StringBuilder("\n Node [value=" + value
				+ ", left=");
		if (left != null && left.value() != null) {
			strB.append(left().value());
		} else {
			strB.append("NULL");
		}
		strB.append(", right=");
		if (right != null && right.value() != null) {
			strB.append(right().value());
		} else {
			strB.append("NULL");
		}
		strB.append("]");
		return "\n " + strB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (left == null ? 0 : left.hashCode());
		result = prime * result + (right == null ? 0 : right.hashCode());
		result = prime * result + (value == null ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TNode<?> other = (TNode<?>) obj;
		if (left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!left.equals(other.left)) {
			return false;
		}
		if (right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!right.equals(other.right)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	public void printTree() {
		final List<List<String>> lines = new ArrayList<List<String>>();
		List<TNode<E>> level = new ArrayList<TNode<E>>();
		List<TNode<E>> next = new ArrayList<TNode<E>>();

		level.add(this);

		int nn = 1;
		int widest = 0;

		while (nn != 0) {
			final List<String> line = new ArrayList<String>();

			nn = 0;

			for (final TNode<E> n : level) {
				if (n == null) {
					line.add(null);
					next.add(null);
					next.add(null);
				} else {
					final E aa = n.value();
					final String temp = "" + aa;
					line.add(temp);
					if (temp.length() > widest) {
						widest = temp.length();
					}

					next.add(n.left());
					next.add(n.right());

					if (n.left() != null) {
						nn++;
					}
					if (n.right() != null) {
						nn++;
					}
				}
			}

			if (widest % 2 == 1) {
				widest++;
			}
			lines.add(line);
			final List<TNode<E>> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
		for (int i = 0; i < lines.size(); i++) {
			final List<String> line = lines.get(i);
			final int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {

					// split node
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = line.get(j) != null ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null) {
								c = '└';
							}
						}
					}
					System.out.print(c);

					// lines and spaces
					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {

						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			// print line of numbers
			for (int j = 0; j < line.size(); j++) {

				String f = line.get(j);
				if (f == null) {
					f = "";
				}
				final int gap1 = (int) Math.ceil(perpiece / 2f - f.length()
						/ 2f);
				final int gap2 = (int) Math.floor(perpiece / 2f - f.length()
						/ 2f);

				// a number
				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;
		}
	}

}
