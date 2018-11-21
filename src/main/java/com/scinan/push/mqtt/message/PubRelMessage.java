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

public class PubRelMessage extends RetryableMessage {

	public PubRelMessage(int messageId) {
		super(Type.PUBREL);
		setQos(QoS.AT_LEAST_ONCE);
		setMessageId(messageId);
	}

	public PubRelMessage(Header header) throws IOException {
		super(header);
	}

//	@Override
//	protected int messageLength() {
//		int length = 0;
//		length += (getQos() == QoS.AT_MOST_ONCE) ? 0 : 2;
//		return length;
//	}

	public String toString() {
		StringBuffer result = new StringBuffer("");
		result.append(getHeader().toString()).append(",");
		result.append("msgLength:").append(messageLength()).append(",");
//		if (getQos() != QoS.AT_MOST_ONCE) {
			result.append("messageId:").append(getMessageId()).append(",");
//		}
		return result.toString();
	}
}
