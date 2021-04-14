/**
 *
 * $Id$
 */
package org.sysadl.validation;

import org.sysadl.Expression;
import org.sysadl.Statement;

/**
 * A sample validator interface for {@link org.sysadl.SwitchClause}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface SwitchClauseValidator {
	boolean validate();

	boolean validateValue(Expression value);
	boolean validateBody(Statement value);
}