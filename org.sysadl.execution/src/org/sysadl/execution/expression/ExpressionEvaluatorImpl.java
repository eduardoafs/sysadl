package org.sysadl.execution.expression;

import org.sysadl.context.SysADLContext;
import org.sysadl.context.exceptions.ContextException;
import org.sysadl.context.exceptions.InvalidExpression;

import org.sysadl.AdditiveExpression;
import org.sysadl.AssignmentExpression;
import org.sysadl.BooleanLiteralExpression;
import org.sysadl.ClassificationExpression;
import org.sysadl.ConditionalLogicalExpression;
import org.sysadl.ConditionalTestExpression;
import org.sysadl.EnumValueLiteralExpression;
import org.sysadl.EqualityExpression;
import org.sysadl.EqualityOperator;
import org.sysadl.Expression;
import org.sysadl.FeatureReference;
import org.sysadl.InstanceCreationExpression;
import org.sysadl.LeftHandSide;
import org.sysadl.LogicalExpression;
import org.sysadl.MultiplicativeExpression;
import org.sysadl.MultiplicativeOperator;
import org.sysadl.NameExpression;
import org.sysadl.NamedElement;
import org.sysadl.NaturalLiteralExpression;
import org.sysadl.NullLiteralExpression;
import org.sysadl.PropertyAccessExpression;
import org.sysadl.RelationalExpression;
import org.sysadl.RelationalOperator;
import org.sysadl.ShiftExpression;
import org.sysadl.StringLiteralExpression;
import org.sysadl.SysADLFactory;
import org.sysadl.ThisExpression;
import org.sysadl.TypeUse;

public class ExpressionEvaluatorImpl extends ExpressionEvaluator {

	/**
	 * ConditionalTestExpression
	 * Format: op1 ? op2 : op3
	 * @returns op2 if op1 is true, op3 otherwise
	 */
	@Override
	public Object evaluate(ConditionalTestExpression e, SysADLContext context) throws ContextException {
		Object test = evaluate(e.getOp1(), context);
		Expression ob2 = e.getOp2();
		if (ob2==null) return test; // if ob2 is null, there is nothing after test
		
		if (test instanceof Boolean) return ((Boolean)test ? evaluate(ob2, context) : evaluate(e.getOp3(), context));
		// case test is not boolean, will always execute the second part
		else return evaluate(e.getOp3(), context);
	}

	/**
	 * ConditionalLogicalExpression
	 * Format: op1 operator op2
	 * @returns op1 and/or op2, if one of the operands is not boolean, returns false 
	 */
	@Override
	public Object evaluate(ConditionalLogicalExpression e, SysADLContext context) throws ContextException {
		Object leftSide = evaluate(e.getOp1(), context);
		Expression rightSide = e.getOp2();
		if (rightSide==null) return leftSide; 
		
		String operator = e.getOperator();
		if (operator.compareTo("&&")==0) { // operator is &&
			if (leftSide instanceof Boolean && (Boolean) leftSide) { // Right side will only be evaluate if leftSide is true
				return evaluate(rightSide, context);
			} else {
				return false; // if one of the objects is not boolean, returns false automatically
			}
		} else { // operator is ||
			if (leftSide instanceof Boolean) {
				if  ((Boolean) leftSide) return true;
				else return evaluate(rightSide, context);
			} else {
				// what to do ih leftSide is not a boolean? right now, return false automatically
				return false;
			}
		}
	}

	/**
	 * LogicalExpression
	 * Format: op1 operator op2
	 * @returns op1 and/exclusiveOr/inclusiveOr op2, if one of the operands is not boolean, returns false
	 */
	@Override
	public Object evaluate(LogicalExpression e, SysADLContext context) throws ContextException {
		Object leftSide = evaluate(e.getOp1(), context);
		Expression rightSide = e.getOp2();
		if (rightSide == null) return leftSide;
		
		String operator = e.getOperator();
		if (operator.compareTo("&")==0) { // and
			if (leftSide instanceof Boolean && (Boolean) leftSide) { // Right side will only be evaluate if leftSide is true
				return evaluate(rightSide, context);
			} else {
				return false; // if one of the objects is not boolean, returns false automatically
			}
		} else if (operator.compareTo("^")==0) { // exclusive or
			Object rightSideValue = evaluate(rightSide, context);
			if ((leftSide instanceof Boolean) && (rightSideValue instanceof Boolean)) {
				if  ((Boolean) leftSide) {
					return !((Boolean) rightSideValue); // if left is true, returns negation of right
				} else {
					return (Boolean) rightSideValue; // otherwise returns right
				}
			}
			else {
				return false;
			}
		} else { // inclusive or
			if (leftSide instanceof Boolean) {
				if  ((Boolean) leftSide) return true;
				else return evaluate(rightSide, context);
			} else {
				// what to do ih leftSide is not a boolean? right now, return false automatically
				return false;
			}
		}
	}

	/**
	 * EqualityExpression
	 * @returns op1 ==/!= op2
	 * @fixme probably will need some fixes, this uses the object's equals that shall not work for many cases
	 */
	@Override
	public Object evaluate(EqualityExpression e, SysADLContext context) throws ContextException {
		Object left = evaluate(e.getOp1(), context);
		Expression expRight = e.getOp2();
		if (expRight==null) return left;
		
		Object right = evaluate(expRight, context); 
		// first test, if they have different classes, return false
		if (left.getClass() != right.getClass()) return false; 
		if (e.getOperator() == EqualityOperator.EQUAL) {
			return left.equals(right);
		}
		else return !left.equals(right);
	}

	/**
	 * ClassificationExpression
	 * @returns true if op1 has type defined typeName, false otherwise
	 */
	@Override
	public Object evaluate(ClassificationExpression e, SysADLContext context) throws ContextException {
		Expression expr = e.getOp();
		Object obj = evaluate(expr, context);
		
		if (e.getTypeName()==null) return obj;
		
		if (obj instanceof TypeUse) {
			return ((TypeUse) obj).getDefinition().equals(e.getTypeName());
		}
		return false;
	}

	/**
	 * RelationalExpression
	 * @returns op1 operator op2, boolean values
	 * @fixme only works for integers
	 */
	@Override
	public Object evaluate(RelationalExpression e, SysADLContext context) throws ContextException {
		Object op1 = evaluate(e.getOp1(), context);
		Expression expr = e.getOp2();
		if (expr==null) return op1;
		Object op2 = evaluate(expr, context);
		
		if ((op1 instanceof Integer) && (op2 instanceof Integer)) {
			switch (e.getOperator().getValue()) {
				case RelationalOperator.GREATER_VALUE:
					return (Integer) op1 > (Integer)op2;
				case RelationalOperator.GREATER_EQUAL_VALUE:
					return (Integer) op1 >= (Integer)op2;
				case RelationalOperator.LESS_VALUE:
					return (Integer) op1 < (Integer)op2;
				case RelationalOperator.LESS_EQUAL_VALUE:
					return (Integer) op1 <= (Integer)op2;
				default:
			}
		}
		return false; // by default it returns false
	}

	@Override
	public Object evaluate(ShiftExpression e, SysADLContext context) throws ContextException {
		Expression exprLeft = e.getOp1();
		Expression exprRight = e.getOp2();
		
		if (exprRight==null) return evaluate(exprLeft, context);
		// TODO implement if exprRight is not null
		throw new InvalidExpression(e);
	}

	/**
	 * AdditiveExpression
	 * Sum and subtraction of values. 
	 * If any of the operands are strings, the operator + performs a concat and - removes a substring if exists
	 * @returns op1 +/- op2
	 */
	@Override
	public Object evaluate(AdditiveExpression e, SysADLContext context) throws ContextException {
		Expression exprLeft = e.getOp1();
		Expression exprRight = e.getOp2();
		
		if (exprRight==null) return evaluate(exprLeft, context);
		Object left = evaluate(exprLeft, context);
		Object right = evaluate(exprRight, context);

		// FIXME
//		NumericUnaryOperator op = e.getOperator();
//		switch (op.getValue()) {
//			case NumericUnaryOperator.PLUS:
//				if (left instanceof String || right instanceof String) {
//					return ((String) left).concat(right.toString());
//				} else {
//					if (left instanceof Integer && right instanceof Integer)
//						return (Integer) left + (Integer) right; // @fixme only for integer
//				}
//			case NumericUnaryOperator.MINUS:
//				if (left instanceof String || right instanceof String) {
//					return ((String) left).replace((right.toString()),"");
//				} else {
//					if (left instanceof Integer && right instanceof Integer)
//						return (Integer) left - (Integer) right; // @fixme only for integer
//				}
//			default:
//		}
		return null;
	}

	/**
	 * MultiplicativeExpression
	 * Multiplication, division and mod
	 * @fixme Only for integers, for now
	 * @returns op1 (* | / | %) op2 
	 */
	@Override
	public Object evaluate(MultiplicativeExpression e, SysADLContext context) throws ContextException {
		Object op1 = evaluate(e.getOp1(), context);
		Expression expr2 = e.getOp2();
		if (expr2==null) return op1;
		Object op2 = evaluate(expr2, context);
		
		MultiplicativeOperator op = e.getOperator();
		if (op1 instanceof Integer && op2 instanceof Integer) {
			switch (op.getValue()) {
				case MultiplicativeOperator.STAR_VALUE:
					return (Integer) op1 * (Integer) op2;
				case MultiplicativeOperator.SLASH_VALUE:
					return (Integer) op1 / (Integer) op2;
				case MultiplicativeOperator.REM_VALUE:
					return (Integer) op1 % (Integer) op2;
				default:
			}
		}
		return null; // @TODO implement for other types
	}

	/**
	 * NameExpression
	 * @returns NamedElement, automatically done by Xtext
	 */
	@Override
	public Object evaluate(NameExpression e, SysADLContext context) throws ContextException {
		return context.get(e.getCite());
	}

	/**
	 * BooleanLiteralExpression
	 * @returns boolean value
	 */
	@Override
	public Object evaluate(BooleanLiteralExpression e, SysADLContext context) throws ContextException {
		return e.isIsTrue();
	}

	/**
	 * NaturalLiteralExpression
	 * @returns double value
	 */
	@Override
	public Object evaluate(NaturalLiteralExpression e, SysADLContext context) throws ContextException {
		return e.getInt_value();
	}

	/**
	 * StringLiteralExpression
	 * @returns string value
	 */
	@Override
	public Object evaluate(StringLiteralExpression e, SysADLContext context) throws ContextException {
		return e.getStr_value();
	}

	@Override
	public Object evaluate(ThisExpression e, SysADLContext context) throws ContextException {
		return context.getThis();
	}

	@Override
	public Object evaluate(PropertyAccessExpression e, SysADLContext context) throws ContextException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object evaluate(FeatureReference e, SysADLContext context) throws ContextException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * AssignmentExpression
	 * One of the most important kinds of expression, updates the context table
	 * @TODO only works for default assignment operator: = 
	 */
	@Override
	public Object evaluate(AssignmentExpression e, SysADLContext context) throws ContextException {
		NamedElement tar = (NamedElement) evaluate(e.getLhs(), context);
		Object value = evaluate(e.getV(), context);
		context.add((NamedElement)tar, value);
		return value; // beside putting in the table, it returns the value
	}

	/**
	 * LeftHandSize
	 * 
	 * Can be a property access or a nameExpression, for now, just a name expression
	 * @TODO implement the other cases
	 * This is the only evaluate that returns a NamedElement
	 */
	@Override
	public Object evaluate(LeftHandSide e, SysADLContext context) throws ContextException {
		return e.getTarget().getCite(); // returns the object itself, not its value
	}

	/**
	 * EnumValueLiteralExpression
	 * 
	 * Returns the EnumLiteralValue for the given expression, these are used on attribution or comparison only
	 */
	@Override
	public Object evaluate(EnumValueLiteralExpression e, SysADLContext context) throws ContextException {
		return e.getEnumValue();
	}

	/**
	 * NullLiteralExpression
	 * 
	 * Null values, just return null
	 */
	@Override
	public Object evaluate(NullLiteralExpression e, SysADLContext context) throws ContextException {
		return null;
	}

	/**
	 * InstanceCreationExpression
	 * 
	 * Creates and return a new TypeUse object
	 */
	@Override
	public Object evaluate(InstanceCreationExpression e, SysADLContext context) throws ContextException {
		TypeUse t = SysADLFactory.eINSTANCE.createTypeUse();
		t.setDefinition(e.getType());
		t.setName("new");
		return t;
	}

}
