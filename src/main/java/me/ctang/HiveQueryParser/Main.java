package me.ctang.HiveQueryParser;

import java.util.ArrayList;

import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.HiveParser;

public class Main 
{
    public static void main( String[] args )
    {
    	String query = "SELECT username, password from Users";
    	
    	// create a AST builder
		Builder bd = new Builder();
		// try to build an AST with the given query
		ASTNode node = bd.buildASTTree(query);
		if(node == null) {
			System.out.println("Parse query error...");
			System.exit(-1);
		}
		
		// create an instance of information extractor
		InfoExtract extractor = new InfoExtract(node);
		
		// get how many tables accessed in this query
		// the return value should be 1, since only the 'Users' table is accessed
		int count = extractor.getTokenCount(HiveParser.TOK_TABREF);
		System.out.println("The number of accessed tables: " + count);
		
		// we can also get the counts of multiple tokens 
		ArrayList<Integer> tokenTypes = new ArrayList<Integer>();
		tokenTypes.add(HiveParser.TOK_TABLE_OR_COL);	// columns in the query
		tokenTypes.add(HiveParser.TOK_SELECT);			// select tokens
		
		// extract counts of multiple tokens at the same time 
		ArrayList<Integer> counts = extractor.getMultiCounts(tokenTypes);
		for(Integer c : counts) {
			System.out.println(c);
		}
    }
}
