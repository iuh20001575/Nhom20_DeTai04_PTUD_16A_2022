package utils;

import java.util.Stack;

import entity.PanelUI;

/**
 * Dùng để chứa các giao diện LIFO
 *
 */
public class StackPanel {
	private static Stack<PanelUI> stack = new Stack<>();

	/**
	 * Thêm một màn hình vào stack
	 * 
	 * @param jFrame
	 */
	public static void push(PanelUI jPanel) {
		stack.push(jPanel);
	}

	/**
	 * Xóa màn hình top khỏi stack
	 * 
	 * @return màn hình đã xóa
	 */
	public static PanelUI pop() {
		return stack.pop();
	}

	/**
	 * Kiểm tra stack có rỗng hay không?
	 * 
	 * @return true nếu stack rỗng
	 */
	public static boolean empty() {
		return stack.empty();
	}

	/**
	 * Get màn hình top
	 * 
	 * @return
	 */
	public static PanelUI peek() {
		return stack.peek();
	}

	public static Stack<PanelUI> name() {
		return stack;
	}
}