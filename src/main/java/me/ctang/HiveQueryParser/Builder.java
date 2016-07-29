package me.ctang.HiveQueryParser;

import java.io.IOException;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.ParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.apache.hadoop.hive.ql.session.SessionState;
import org.apache.hadoop.hive.ql.Context;

public class Builder {
	private HiveConf conf = new HiveConf();
	private Context ctx = null;
	private ParseDriver pd = null;
	
    public Builder() {
    	// disable SQL11 keywords support
    	conf.setBoolVar(HiveConf.ConfVars.HIVE_SUPPORT_SQL11_RESERVED_KEYWORDS, false);
        SessionState.start(conf);
        try {
			ctx = new Context(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
        pd = new ParseDriver();
    }
    
    
	public HiveConf getConf() {
		return conf;
	}


	public void setConf(HiveConf conf) {
		this.conf = conf;
	}


	public Context getCtx() {
		return ctx;
	}


	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}


	public ParseDriver getPd() {
		return pd;
	}


	public void setPd(ParseDriver pd) {
		this.pd = pd;
	}

	
	public ASTNode buildASTTree(String query) {
		try {
			ASTNode astNode = this.pd.parse(query, this.ctx);
			return astNode;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// return null if the ParseDriver didn't parse the query successfully
		return null;
	}
}
