package utils;

import java.util.Stack;

import javax.swing.JFrame;

/**
 * Dúng để chứa các giao diện LIFO
 * 
 * @author ThaoHa
 *
 */
public class StackFrame {
	private static Stack<JFrame> stack = new Stack<>();

	/**
	 * Thêm một màn hình vào stack
	 * 
	 * @param jFrame
	 */
	public static void push(JFrame jFrame) {
		stack.push(jFrame);
	}

	/**
	 * Xóa màn hình top khỏi stack
	 * 
	 * @return màn hình đã xóa
	 */
	public static JFrame pop() {
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
	public static JFrame peek() {
		return stack.peek();
	}
}
