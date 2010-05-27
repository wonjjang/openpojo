/**
 * Copyright (C) 2010 Osman Shoukry
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.openpojo.validation.test.impl;

import com.openpojo.log.LoggerFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoField;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.utils.ValidationHelper;

/**
 * This Rule ensures that all Fields are initialized to null.<br>
 * This rule ignores fields that are marked as final or primitive types since neither can be initialized to null.<br>
 *
 * @author oshoukry
 */
public class DefaultValuesNullTester implements Tester {

    public void run(final PojoClass pojoClass) {
        if (!pojoClass.isConcrete()) {
            LoggerFactory.getLogger(this.getClass()).warn(
                    "Attempt to execute behavioural test on non-concrete class=[{0}] ignored,"
                            + " consider using FilterNonConcrete class when calling PojoClassFactory", pojoClass);
        } else {
            Object classInstance = null;

            classInstance = ValidationHelper.getNewInstance(pojoClass);
            for (PojoField fieldEntry : pojoClass.getPojoFields()) {
                if (!fieldEntry.isPrimitive() && !fieldEntry.isFinal()) {
                    Affirm.affirmNull(String.format("Expected null value for for field=[%s]", fieldEntry), fieldEntry
                            .get(classInstance));
                }
            }
        }
    }

}
