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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.sas.sling.resource.parser.conversion.Null;
import com.sas.sling.resource.parser.node.Node;
import com.sas.sling.resource.parser.node.Visitor;

public class ValueVisitor implements Visitor<Function<Resource, Object>, Void> {

	private Map<String, Function<Resource, Object>> functions = new HashMap<>();

	@Override
	public Function<Resource, Object> visit(Node node, Void param) {
		switch (node.getType()) {
		case FUNCTION:
			break;
		case NULL:
			return resource -> new Null();
		case PROPERTY:
			return resource -> {
				Object value = resource.adaptTo(ValueMap.class).get(node.getValue());
				if (value instanceof Boolean) {
					return value.toString();
				}
				return value;
			};
		default:
			return resource -> node.getValue();
		}
		// will only get here in the case of the 'FUNCTION' switch case
		switch (node.getValue()) {
		case "name":
			return resource -> resource.getName();
		case "date":
			List<Function<Resource, Object>> children = node.visitChildren(this, param);
			return resource -> {
				if (children.isEmpty()) {
					return null;
				}
				String dateString = children.get(0).apply(resource).toString();
				String formatString = null;
				Calendar cal = Calendar.getInstance();
				if (children.size() > 1) {
					formatString = children.get(1).apply(resource).toString();
					SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
					try {
						cal.setTimeInMillis(dateFormat.parse(dateString).getTime());
						return cal;
					} catch (ParseException e) {
						return null;
					}
				} else {
					cal.setTimeInMillis(DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(dateString, OffsetDateTime::from)
							.toInstant().toEpochMilli());
					return cal;
				}
			};
		default:
		}
		return null;
	}

	public Function<Resource, Object> registerFunction(String functionName, Function<Resource, Object> function) {
		return this.functions.put(functionName, function);
	}

	public Function<Resource, Object> removeFunction(String functionName) {
		return this.functions.remove(functionName);
	}

}
