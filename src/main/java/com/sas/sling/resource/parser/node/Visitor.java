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
package com.sas.sling.resource.parser.node;

/**
 * An interface for visiting AST nodes of the RSQL.
 *
 * @param <R> Return type of the visitor's method.
 * @param <A> Type of an optional parameter passed to the visitor's method.
 */
public interface Visitor<R, A> {

	R visit(Node abstractNode, A param);
    
}
