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

import java.util.function.Function;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import com.sas.sling.resource.ResourceLocator;
import com.sas.sling.resource.parser.conversion.Null;
import com.sas.sling.resource.parser.node.Node;
import com.sas.sling.resource.parser.node.NodeType;
import com.sas.sling.resource.parser.node.Visitor;


public class ValueVisitor implements Visitor<Function<Resource,Object>, Void> {

	@Override
	public Function<Resource,Object> visit(Node node, Void param) {
		if (node.getType() == NodeType.STRING){
			return resource -> node.getValue();
		}
		if (node.getType() == NodeType.PROPERTY){
			return resource -> resource.adaptTo(ValueMap.class).get(node.getValue());
		}
		if (node.getType() == NodeType.NULL){
			return resource -> new Null();
		}
		if (node.getType() != NodeType.FUNCTION){
			return resource -> node.getValue();
		}

		switch (node.getValue()){
		case "name":
			return resource -> resource.getName();
		case "child":
			return resource -> resource.getChild(node.getRightOperands().get(0).getValue());
		default:
		}
		return null;
		
	}

}