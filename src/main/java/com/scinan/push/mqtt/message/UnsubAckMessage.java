/*******************************************************************************
 * Copyright 2011 Albin Theander
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.scinan.push.mqtt.message;

import java.io.IOException;
import java.io.OutputStream;

public class UnsubAckMessage extends RetryableMessage {

	public UnsubAckMessage() {
		super(Type.UNSUBACK);
	}

	public UnsubAckMessage(Header header) throws IOException {
		super(header);
	}

	@Override
	protected void writeMessage(OutputStream out) throws IOException {
		super.writeMessage(out);
		out.flush();
	}

//	@Override
//	protected int messageLength() {
//		return 0;
//	}
}
