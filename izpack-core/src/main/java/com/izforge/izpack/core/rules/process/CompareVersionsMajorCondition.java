/*
 * Copyright 2016 Julien Ponge, René Krell and the IzPack team.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izforge.izpack.core.rules.process;

import com.izforge.izpack.api.data.AutomatedInstallData;
import com.izforge.izpack.api.rules.ComparisonOperator;
import com.izforge.izpack.core.data.DefaultVariables;
import com.izforge.izpack.util.Platform;

import java.util.logging.Logger;

public class CompareVersionsMajorCondition extends CompareVersionsCondition
{
    private static final long serialVersionUID = -8392922321054039545L;

    private static final transient Logger logger = Logger.getLogger(CompareVersionsMajorCondition.class.getName());

    public CompareVersionsMajorCondition()
    {
        super(NOT_ASSUME_MISSING_MINOR_PARTS_AS_0);
    }

    public static void main (String args[])
    {
        final String op1="1.8.0_72", op2="1.8";
        final ComparisonOperator operator = ComparisonOperator.LESSEQUAL;
        CompareVersionsMajorCondition condition = new CompareVersionsMajorCondition();
        condition.setInstallData(new AutomatedInstallData(new DefaultVariables(), new Platform(Platform.Name.LINUX)));
        condition.setLeftOperand(op1);
        condition.setOperator(operator);
        condition.setRightOperand(op2);
        System.out.println(op1 + " " + operator.getAttribute() + " " + op2 + ": " + condition.isTrue());
    }
}