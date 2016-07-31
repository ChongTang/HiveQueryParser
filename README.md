# Hive Query Parser

This is a tool that parses a Hive query using Hive's built-in [ParseDriver](https://hive.apache.org/javadocs/r2.1.0/api/org/apache/hadoop/hive/ql/parse/ParseDriver.html).

ParseDriver tries to build an abstract syntax tree (AST) from the given query. If success, it will return an AST with type [ASTNode](https://hive.apache.org/javadocs/r2.1.0/api/org/apache/hadoop/hive/ql/parse/ASTNode.html).

One then can traverse the returned AST to extract any interesting information.

This is an Eclipse project. One can download and import it into Eclipse.
