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
package com.sas.sling.resource.parser.conversion;

/**
 * A converter for Number objects
 */
public class ConverterForNumber implements Converter {

    protected final Number value;

    public ConverterForNumber(final Number val) {
        this.value = val;
    }

	@Override
	public Number getNumber() {
		return value;
	}

	@Override
	public String getString() {
		return value.toString();
	}

}