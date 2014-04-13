package edu.uta.cse.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import org.eclipse.jdt.core.dom.*;

import edu.uta.cse.util.*;

/**
 * This class can parse java context.
 * Input: The content of java source file using String format.
 * 
 * @author Hui Zhou
 * @version 2.0
 * @since 3-14-2014
 * Update: 4-13-2014
 * @see http://blog.csdn.net/flying881114/article/details/6187725
 *  http://www.eclipse.org/articles/article.php?file=Article-JavaCodeManipulation_AST/index.html
 * 
 */
public class CodeExtractor {
	
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
	    // Set the source code character of parser.  
	    parser.setSource(content.toCharArray()); 
	    // Parse and get the AST context result.
	    result = (CompilationUnit) parser.createAST(null);
	}
	
	public CodeExtractor(){
		// Create AST parser.  		
	    parser = ASTParser.newParser(AST.JLS4);
	}
	/**
	 * 	Constructor
	 * @param content
	 * 	The content of java source file. 
	 * 	It can be delivered from IDocument interface.
	 */
	public CodeExtractor(String content){
		// Create AST parser.  		
	    ASTParser parser = ASTParser.newParser(AST.JLS4);
	    // Set the source code character of parser.  
	    parser.setSource(content.toCharArray()); 
	    // Parse and get the AST context result.
	    result = (CompilationUnit) parser.createAST(null);
	}
	
	/**
	 * @since 3-14-2014
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
	    /*if(returnTypes.contains(returnTypeToBeModified))
	    	returnTypes.remove(returnTypeToBeModified);*/
		return returnTypes;
	}
	
	/**
	 * Get global variables.
	 * @since 3-24-2014
	 * @return
	 *  The names of global variables.
	 */
	public List<String> getGlobalFields(){
		List<String> globalFields = new ArrayList<String>();

		// Get types.
		List types = result.types();
		if (types.size() == 0)
			return globalFields;
		// Get type declaration.
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// Get field declaration list.
		FieldDeclaration fieldDec[] = typeDec.getFields();
		for (FieldDeclaration fieldDecEle : fieldDec) {
			for (Object obj : fieldDecEle.fragments()) {
				VariableDeclarationFragment frag = (VariableDeclarationFragment) obj;
				globalFields.add(frag.getName().toString());
				System.out.println("[FIELD_NAME:]" + frag.getName().toString());// For debug
			}
		}
		return globalFields;
	}
	/**
	 *  Get all method parameters.
	 * @since 3-24-2014
	 *	Update: 4-13-2014
	 * @return
	 *  The names of all method parameters.
	 */
	public List<String> getAllMethodParameters(){
		List<String> methodParameters = new ArrayList<String>();
		
		// Record the site of target method.
		int iterator;
		Boolean exist = false;
		
		// Get types. This 'type' is not something like 'void', 'int', 'boolean'...
		List types = result.types();
		
		if (types.size() == 0)
			return methodParameters;
		
		// Get type declaration.
		TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
		// Get methods' names.
		MethodDeclaration methodDec[] = typeDec.getMethods();
		
		for (MethodDeclaration method: methodDec) {
			List<SingleVariableDeclaration> parameters = method.parameters();
			for(SingleVariableDeclaration parameter:parameters){
				// Add parameters to method parameter list.
				if(!methodParameters.contains(parameter.getName().toString()))
					methodParameters.add(parameter.getName().toString());
			}
		}
		
		return methodParameters;
	}
	
	/**
	 * @since 4-13-2014
	 * Get all variables which appear in context except the input parameters of methods.
	 * @return
	 *  The names of all variables.
	 */
	public List<String> getAllVariables(){
		final List<String> variables = new ArrayList<String>();
		// Visitor pattern.
		result.accept(new ASTVisitor(){
			public boolean visit(VariableDeclarationFragment node) {
				SimpleName name = node.getName();
				if(!variables.contains(name.toString())){
					variables.add(name.toString());
				}
				return false;
			}

		});
		return variables;
	}
	
	/**
	 * Get all local fields (local variables) including the global fields.
	 * @param methodName
	 * Target method name.
	 * @return
	 * Fields (local variables) names.
	 */
	public List<String> getLocalFields(final String methodName) {
		final List<String> fields = getGlobalFields();
		// Visitor pattern.
		result.accept(new ASTVisitor() {
			// Find target method.
			public boolean visit(MethodDeclaration node){
				SimpleName name = node.getName();
				if(name.toString().compareTo(methodName) == 0){
					List<SingleVariableDeclaration> parameters = node.parameters();
					for(SingleVariableDeclaration parameter:parameters){
						// Add parameters to list.
							fields.add(parameter.getName().toString());
					}
					
					node.accept(new ASTVisitor(){
						// Find variables declared within the method.
						public boolean visit(VariableDeclarationFragment node){
							SimpleName name = node.getName();
							if (!fields.contains(name.toString())) {
								fields.add(name.toString());
							}
							return false;
						}
					});
				}
				return true;
			}// End outside visit.
		});
		return fields;
	}// End getAllFields.
}
