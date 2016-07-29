package me.ctang.HiveQueryParser;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.parse.ASTNode;

public class InfoExtract {
	private ASTNode node = null;

	public InfoExtract(ASTNode node) {
		if (node == null) {
			System.out.println("Error: the given node is not a valid AST Node...");
			return;
		}
		this.node = node;
	}

	public int getTokenCount(Integer token) {
		int count = 0;
		count = this.getTokenCountHelper(this.node, token);
		return count;
	}

	private int getTokenCountHelper(ASTNode node, Integer token) {
		int count = 0;
		for (int i = 0; i < node.getChildCount(); i++) {
			ASTNode child = (ASTNode) node.getChild(i);
			if (child.getToken().getType() == token) {
				count++;
			} else {
				count += getTokenCountHelper(child, token);
			}
		}
		return count;
	}
	
	public ArrayList<Integer> getMultiCounts(ArrayList<Integer> tokenTypes) {
		int length = tokenTypes.size();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		for(int i=0; i<length; i++) {
			int count = getMultiCountsHelper(this.node, tokenTypes.get(i));
			counts.add(count);
		}
		return counts;
	}
	
	private int getMultiCountsHelper(ASTNode node, int tokenType) {
		int count = 0;
		for (int i = 0; i < node.getChildCount(); i++) {
			ASTNode child = (ASTNode) node.getChild(i);
			if (child.getToken().getType() == tokenType) {
				count++;
			} else {
				count += getMultiCountsHelper(child, tokenType);
			}
		}
		return count;
	}
}
