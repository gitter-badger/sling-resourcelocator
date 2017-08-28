package com.sas.sling.resource.query;
/*
 * Copyright 2016 Jason E Bailey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Optional;

public enum RqlSearchOperation {
    EQUAL("=="), 
    NOT_EQUAL("!="), 
    GREATER_THAN(">"), 
    GREATER_THAN_OR_EQUAL(">="), 
    LESS_THAN("<"), 
    LESS_THAN_OR_EQUAL("<="), 
    IN("in"), 
    NOT_IN("out");
 
    private String operator;
 
    private RqlSearchOperation(String operator) {
        this.operator = operator;
    }
 
    public static Optional<RqlSearchOperation> getSimpleOperator(String operation) {
    	for (RqlSearchOperation value:RqlSearchOperation.values()){
    		if (operation.equals(value.operator)){
    			return Optional.of(value);
    		}
    	}
    	return Optional.empty();
    }
}