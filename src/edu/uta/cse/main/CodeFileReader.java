package edu.uta.cse.main;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.eclipse.jdt.core.dom.*;

import edu.uta.cse.util.*;

/**
 * This class can parse java context.
 * Input: The content of java source file using String format.
 * 
 * @author Hui Zhou
 * @version 1.0
 * @since 3-14-2014
 * @see http://blog.csdn.net/flying881114/article/details/6187725
 */
public class CodeFileReader {
	
	private ASTParser parser;
	/**
	 * The parse result. CompilationUnit means the root.
	 */
	private CompilationUnit result;
	
	private String content;
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
		// Create AST parser.  		
		//ASTParser parser = ASTParser.newParser(AST.JLS4);
	    // Set the source code character of parser.  
	    parser.setSource(content.toCharArray()); 
	    // Parse and get the AST context result.
	    result = (CompilationUnit) parser.createAST(null);
	}
	
	public CodeFileReader(){
		// Create AST parser.  		
	    parser = ASTParser.newParser(AST.JLS4);
	}
	/**
	 * 	Constructor
	 * @param content
	 * 	The content of java source file. 
	 * 	It can be delivered from IDocument interface.
	 */
	public CodeFileReader(String content){
		// Create AST parser.  		
	    ASTParser parser = ASTParser.newParser(AST.JLS4);
	    // Set the source code character of parser.  
	    parser.setSource(content.toCharArray()); 
	    // Parse and get the AST context result.
	    result = (CompilationUnit) parser.createAST(null);
	}
	
	/**
	 * When user double click the method return type, 
	 * this method will provide the probable content to replace the former type.
	 * @param returnTypeToBeModified
	 *  The original method return type which user probably wants to change.
	 * @return
	 * 	New probable method return types.
	 */
	public List<String> getMethodReturnType(String returnTypeToBeModified) {
		
		List<String> returnTypes = new ArrayList<String>();
	
		// Get types.
		List types = result.types();
		if(types.size() == 0)
			return returnTypes;
		// Get type declaration.  
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// Get field declaration list. 
	    FieldDeclaration fieldDec[] = typeDec.getFields();
	    for(int i = 0; i < Constant.basicFieldType.length; i++){
	    	returnTypes.add(Constant.basicFieldType[i]);
	    }
	    
		for (FieldDeclaration fieldDecEle : fieldDec) {
			Modifier modify = null;
			for (Object modifiObj : fieldDecEle.modifiers()) {
				modify = (Modifier) modifiObj;
			}

			if (modify == null)
				break;
			// Add field type to list.
			if (!returnTypes.contains(modify.toString()))
				returnTypes.add(modify.toString());
		}
		// Remove the original return type.
	    if(returnTypes.contains(returnTypeToBeModified))
	    	returnTypes.remove(returnTypeToBeModified);
		return returnTypes;
	}
}
