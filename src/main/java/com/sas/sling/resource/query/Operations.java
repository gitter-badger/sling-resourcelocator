/*
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
package com.sas.sling.resource.query;

import java.util.Optional;

public enum Operations {
	EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL;


	public static Optional<Operations> getSimpleOperator(String operation) {
		switch (operation) {
		case "==":
		case "is":
			return Optional.of(EQUAL);
		case "!=":
			return Optional.of(NOT_EQUAL);
		case ">":
			return Optional.of(GREATER_THAN);
		case ">=":
			return Optional.of(GREATER_THAN_OR_EQUAL);
		case "<":
			return Optional.of(LESS_THAN);
		case "<=":
			return Optional.of(LESS_THAN_OR_EQUAL);
		}
		return Optional.empty();
	}
}